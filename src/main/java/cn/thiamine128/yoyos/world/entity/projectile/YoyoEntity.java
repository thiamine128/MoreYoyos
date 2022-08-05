package cn.thiamine128.yoyos.world.entity.projectile;

import cn.thiamine128.yoyos.capability.IYoyoCapability;
import cn.thiamine128.yoyos.capability.YoyosCapabilities;
import cn.thiamine128.yoyos.world.item.AbstractYoyoItem;
import cn.thiamine128.yoyos.world.entity.YoyosEntityType;
import cn.thiamine128.yoyos.world.item.enchantment.YoyosEnchantmentHelper;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.*;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class YoyoEntity extends Projectile {
    public Vec3 targetPos;
    public int life;
    public int attackInterval;
    public int cooldownTick;
    public AbstractYoyoItem yoyo;
    public double maxLength;
    public double movementSpeed;
    public double attackDamage;
    public boolean hitted;
    public double power;

    private static final EntityDataAccessor<ItemStack> YOYO_ITEM = SynchedEntityData.defineId(YoyoEntity.class, EntityDataSerializers.ITEM_STACK);
    private static final EntityDataAccessor<Boolean> PROJECTILE = SynchedEntityData.defineId(YoyoEntity.class, EntityDataSerializers.BOOLEAN);

    public YoyoEntity(EntityType<? extends Projectile> p_37248_, Level p_37249_) {
        super(p_37248_, p_37249_);

        this.targetPos = Vec3.ZERO;
        this.life = 0;
        this.noCulling = true;
        this.hitted = false;
    }

    public YoyoEntity(Level level, Entity owner, ItemStack yoyoItem) {
        this(YoyosEntityType.YOYO.get(), level);

        if (owner instanceof Player) {
            Player player = (Player) owner;
            float f2 = Mth.lerp(player.getYRot(), player.yBodyRotO, player.yBodyRot) * ((float)Math.PI / 180F);
            double d0 = (double)Mth.sin(f2);
            double d1 = (double)Mth.cos(f2);
            double d2 = (double)0.35D;

            double handX = player.getX() - d1 * d2 - d0 * 0.3D;
            double handY = player.getEyeHeight() + player.getY() - 1.0D;
            double handZ = player.getZ() - d0 * d2 + d1 * 0.3D;

            this.setPos(handX, handY, handZ);
        }

        this.setOwner(owner);
        this.setYoyoItem(yoyoItem);

        this.attackDamage = 1.0d;

        if (owner instanceof LivingEntity) {
            attackDamage = ((LivingEntity) owner).getAttributeValue(Attributes.ATTACK_DAMAGE);
        }
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(YOYO_ITEM, ItemStack.EMPTY);
        this.entityData.define(PROJECTILE, false);
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> dataAccessor) {
        super.onSyncedDataUpdated(dataAccessor);

        if (dataAccessor.equals(YOYO_ITEM)) {
            ItemStack yoyoItem = this.getYoyoItem();
            if (yoyoItem.getItem() instanceof AbstractYoyoItem) {
                this.yoyo = (AbstractYoyoItem) yoyoItem.getItem();

                this.maxLength = yoyo.getStringLength();
                this.movementSpeed = yoyo.getSpeed();
                this.attackInterval = yoyo.getAttackInterval();
                this.attackInterval += YoyosEnchantmentHelper.getSpeedBonus(yoyoItem);
                this.cooldownTick = this.attackInterval;
            } else {
                this.yoyo = null;
                this.attackInterval = 20;
                this.cooldownTick = 0;

                this.maxLength = 6.0d;
                this.movementSpeed = 0.8d;
                this.attackInterval = 20;
            }
        }
    }

    @Override
    public void lerpTo(double p_19896_, double p_19897_, double p_19898_, float p_19899_, float p_19900_, int p_19901_, boolean p_19902_) {

    }

    @Override
    public void tick() {
        super.tick();

        ++this.life;

        ++this.cooldownTick;

        Entity owner = this.getOwner();
        if (owner == null) {
            this.discard();
            return;
        } else if (owner instanceof Player && !this.isProjectile()){
            Player player = (Player) owner;
            if (!player.isUsingItem() || !player.getUseItem().sameItem(this.getYoyoItem())) {
                this.discard();
                return;
            }
        }

        if (this.yoyo != null) {
            this.yoyo.processEffects(this);
        }

        Entity hitting = null;

        HitResult hitResult = null;


        if (owner != null) {
            hitResult = getHitResult(this, this::canHitEntity);
            this.setRot(owner.getYRot(), owner.getXRot());

            if (hitResult.getType() == HitResult.Type.ENTITY) {
                hitting = ((EntityHitResult) hitResult).getEntity();
            }

            this.targetPos = hitResult.getLocation();
        }

        boolean flag = (hitting != null);

        if (!this.level.isClientSide) {
            List<Entity> entities = this.level.getEntities(this, this.getBoundingBox().inflate(0.3d), this::canHitEntity);
            for (Entity entity : entities) {
                if (flag && entity.getId() == hitting.getId())
                    flag= false;
                this.onHitEntity(new EntityHitResult(entity));
            }

            if (flag && this.targetPos.distanceTo(this.position()) < 0.3d) {
                this.onHitEntity(new EntityHitResult(hitting));
            }


            if (entities.size() > 0 || (hitting != null && hitting.position().distanceTo(this.position()) < 0.3d) || (this.targetPos.distanceTo(this.position()) < 0.05f)) {
                if (!this.hitted) {
                    this.yoyo.onHit(this);
                    this.hitted = true;

                    if (this.isProjectile()) {
                        this.yoyo.onFinsh(this);
                        this.discard();
                        return;
                    }
                }
            }
        }

        this.updatePos();
    }

    @Override
    public void remove(RemovalReason p_146834_) {
        super.remove(p_146834_);
        this.updateOwnerInfo(null);
    }

    @Override
    public void setOwner(@Nullable Entity p_37263_) {
        super.setOwner(p_37263_);
        this.updateOwnerInfo(this);
    }

    @Override
    public void onClientRemoval() {
        super.onClientRemoval();
        this.updateOwnerInfo(null);
    }

    @Override
    protected void onHitEntity(EntityHitResult hitResult) {
        super.onHitEntity(hitResult);
        this.yoyo.onCollideWithEntity(this, hitResult.getEntity());
        if (this.cooldownTick >= this.attackInterval) {
            if (this.getOwner() instanceof Player) {
                Player player = (Player) this.getOwner();
                player.getUseItem().hurtAndBreak(1, player, (livingEntity) -> {
                    livingEntity.broadcastBreakEvent(EquipmentSlot.MAINHAND);
                    this.discard();
                });
            }

            if (this.yoyo != null)
                this.yoyo.onHitEntity(this, hitResult.getEntity());
            this.cooldownTick = 0;
        }
    }

    @Override
    public boolean shouldRenderAtSqrDistance(double p_37125_) {
        return p_37125_ < 4096.0D;
    }

    public ItemStack getYoyoItem() {
        return this.entityData.get(YOYO_ITEM);
    }

    public void setYoyoItem(ItemStack yoyoItem) {
        this.entityData.set(YOYO_ITEM, yoyoItem);
    }

    public void updatePos() {
        Vec3 delta = this.targetPos.subtract(this.position());
        if (delta.length() < this.movementSpeed) {
            this.setPos(this.targetPos);
        } else {
            this.setPos(this.position().add(delta.normalize().scale(this.movementSpeed)));
        }
    }

    public void updateOwnerInfo(YoyoEntity yoyo) {
        Entity owner = this.getOwner();
        if (owner != null && owner instanceof Player) {
            Player player = (Player) owner;
            LazyOptional<IYoyoCapability> cap = player.getCapability(YoyosCapabilities.YOYO);
            cap.ifPresent(c -> {
                c.setYoyo(yoyo);
            });
        }
    }

    public static HitResult getHitResult(YoyoEntity entity, Predicate<Entity> predicate) {
        Vec3 direction = entity.getOwner().getForward().normalize().scale(entity.maxLength);
        Level level = entity.level;
        Vec3 start = entity.getOwner().getEyePosition();
        Vec3 end = start.add(direction);

        AABB testBoundingBox = entity.getBoundingBox().move(start.subtract(entity.position()));
        HitResult hitresult = level.clip(new ClipContext(start, end, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entity));

        if (hitresult.getType() != HitResult.Type.MISS) {
            end = hitresult.getLocation();
        }

        HitResult hitresult1 = getEntityHitResult(level, entity, start, end, testBoundingBox.expandTowards(direction).inflate(1.0D), predicate);
        if (hitresult1 != null) {
            hitresult = hitresult1;
        }

        return hitresult;
    }

    @javax.annotation.Nullable
    public static EntityHitResult getEntityHitResult(Level p_37305_, Entity p_37306_, Vec3 p_37307_, Vec3 p_37308_, AABB p_37309_, Predicate<Entity> p_37310_) {
        return getEntityHitResult(p_37305_, p_37306_, p_37307_, p_37308_, p_37309_, p_37310_, 0.3F);
    }

    @javax.annotation.Nullable
    public static EntityHitResult getEntityHitResult(Level p_150176_, Entity p_150177_, Vec3 p_150178_, Vec3 p_150179_, AABB p_150180_, Predicate<Entity> p_150181_, float p_150182_) {
        double d0 = Double.MAX_VALUE;
        Entity entity = null;
        Vec3 pos = null;

        for(Entity entity1 : p_150176_.getEntities(p_150177_, p_150180_, p_150181_)) {
            AABB aabb = entity1.getBoundingBox().inflate((double)p_150182_);
            Optional<Vec3> optional = aabb.clip(p_150178_, p_150179_);
            if (optional.isPresent()) {
                double d1 = p_150178_.distanceToSqr(optional.get());
                if (d1 < d0) {
                    entity = entity1;
                    d0 = d1;
                    pos = optional.get();
                }
            }
        }

        return entity == null ? null : new EntityHitResult(entity, pos);
    }

    public void asProjectile() {
        this.entityData.set(PROJECTILE, true);
    }

    public boolean isProjectile() {
        return this.entityData.get(PROJECTILE);
    }

    public RandomSource getRandom() {
        return this.random;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public double getPower() {
        return this.power;
    }
}
