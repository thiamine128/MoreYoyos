package cn.thiamine128.yoyos.world.item;

import cn.thiamine128.yoyos.capability.IYoyoCapability;
import cn.thiamine128.yoyos.capability.YoyosCapabilities;
import cn.thiamine128.yoyos.world.entity.projectile.YoyoEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.util.LazyOptional;

public class SimpleYoyoItem extends AbstractYoyoItem{
    public SimpleYoyoItem(int maxDamage, double attackDamage, double stringLength, double speed, float knockback, int attackInterval) {
        super((new Properties()).tab(YoyosCreativeModeTabs.YOYOS).durability(maxDamage), attackDamage, stringLength, speed, knockback, attackInterval);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (!level.isClientSide) {
            LazyOptional<IYoyoCapability> cap = player.getCapability(YoyosCapabilities.YOYO);
            cap.ifPresent(c -> {
                if (c.getYoyo() != null) {
                    c.getYoyo().discard();
                }
            });
            YoyoEntity yoyo = new YoyoEntity(level, player, itemStack);
            yoyo.setXRot(player.getXRot());
            yoyo.setYRot(player.getYRot());
            level.addFreshEntity(yoyo);
        }
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(itemStack);
    }


    @Override
    public void releaseUsing(ItemStack p_41412_, Level level, LivingEntity user, int p_41415_) {
        super.releaseUsing(p_41412_, level, user, p_41415_);
        if (!level.isClientSide) {
            if (user instanceof Player) {
                Player player = (Player) user;
                LazyOptional<IYoyoCapability> cap = player.getCapability(YoyosCapabilities.YOYO);
                cap.ifPresent(c -> {
                    if (c.getYoyo() != null) {
                        c.getYoyo().discard();
                    }
                });
            }
        }
    }


    @Override
    public ItemStack finishUsingItem(ItemStack p_41409_, Level level, LivingEntity user) {
        if (!level.isClientSide) {
            if (user instanceof Player) {
                Player player = (Player) user;
                LazyOptional<IYoyoCapability> cap = player.getCapability(YoyosCapabilities.YOYO);
                cap.ifPresent(c -> {
                    if (c.getYoyo() != null) {
                        c.getYoyo().discard();
                    }
                });
            }
        }
        return super.finishUsingItem(p_41409_, level, user);
    }

    @Override
    public int getEnchantmentValue() {
        return 14;
    }


    public void onHitEntity(YoyoEntity yoyo, Entity entity) {
        DamageSource damageSource;
        double knockback = 1.0d;

        if (yoyo.getOwner() instanceof LivingEntity) {
            knockback = ((LivingEntity) yoyo.getOwner()).getAttributeValue(Attributes.ATTACK_KNOCKBACK);
        }

        if (yoyo.getOwner() instanceof Player) {
            damageSource = DamageSource.playerAttack((Player) yoyo.getOwner());
            ((Player) yoyo.getOwner()).setLastHurtMob(entity);
        } else {
            damageSource = DamageSource.GENERIC;
        }

        entity.hurt(damageSource, (float) this.getDamage(yoyo, entity));
        if (entity instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) entity;
            Vec3 dir = yoyo.getForward().normalize();

            livingEntity.knockback(knockback, -dir.x, -dir.z);
            livingEntity.invulnerableTime = Math.max(10 + yoyo.attackInterval - 1, 0);
        }
    }


    @Override
    public void onCollideWithEntity(YoyoEntity yoyo, Entity entity) {

    }

    @Override
    public void processEffects(YoyoEntity yoyo) {

    }

    @Override
    public boolean isFunctional() {
        return false;
    }

    @Override
    public void onHit(YoyoEntity yoyo) {

    }

    @Override
    public void onFinsh(YoyoEntity yoyo) {

    }
}
