package cn.thiamine128.yoyos.world.item;

import cn.thiamine128.yoyos.world.entity.projectile.YoyoEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class IronGolemYoyoItem extends FunctionalYoyoItem {
    public IronGolemYoyoItem(int maxDamage, double attackDamage, double stringLength, double speed, float knockback, int attackInterval) {
        super(maxDamage, attackDamage, stringLength, speed, knockback, attackInterval, 150, 60);
    }

    @Override
    public void onHitEntity(YoyoEntity yoyo, Entity entity) {
        super.onHitEntity(yoyo, entity);

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

        entity.hurt(damageSource, (float) (this.getDamage(yoyo, entity) + 20 * yoyo.getPower()));
        if (entity instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) entity;
            Vec3 dir = yoyo.getForward().normalize();

            livingEntity.knockback(knockback, -dir.x, -dir.z);
            livingEntity.invulnerableTime = Math.max(10 + yoyo.attackInterval - 1, 0);
        }
        double knockbackResistance;
        if (entity instanceof LivingEntity) {
            LivingEntity livingentity = (LivingEntity)entity;
            knockbackResistance = livingentity.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE);
        } else {
            knockbackResistance = 0.0D;
        }

        double up = Math.max(0.0D, 1.0 - knockbackResistance);
        entity.setDeltaMovement(entity.getDeltaMovement().add(new Vec3(0d, 0.4d * up, 0d)));
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, level, components, tooltipFlag);
        components.add(new TranslatableComponent("moreyoyos.tip6").withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
    }
}
