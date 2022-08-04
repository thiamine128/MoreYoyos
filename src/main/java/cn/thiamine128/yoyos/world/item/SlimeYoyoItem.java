package cn.thiamine128.yoyos.world.item;

import cn.thiamine128.yoyos.world.entity.projectile.YoyoEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SlimeYoyoItem extends SimpleYoyoItem{
    public SlimeYoyoItem(int maxDamage, double attackDamage, double stringLength, double speed, float knockback, int attackInterval) {
        super(maxDamage, attackDamage, stringLength, speed, knockback, attackInterval);
    }

    @Override
    public void onHitEntity(YoyoEntity yoyo, Entity entity) {
        super.onHitEntity(yoyo, entity);
    }

    @Override
    public void onCollideWithEntity(YoyoEntity yoyo, Entity entity) {
        super.onCollideWithEntity(yoyo, entity);
        entity.stuckSpeedMultiplier = new Vec3(0.1f, 0.1f, 0.1f);
    }

    @Override
    public void processEffects(YoyoEntity yoyo) {
        if (yoyo.getRandom().nextFloat() < 0.5f) {
            int particleCount = yoyo.getRandom().nextInt(9);
            for(int j = 0; j < particleCount; ++j) {
                float f = yoyo.getRandom().nextFloat() * ((float)Math.PI * 2F);
                float f1 = yoyo.getRandom().nextFloat() * 0.5F + 0.5F;
                float f2 = Mth.sin(f) * 0.5F * f1;
                float f3 = Mth.cos(f) * 0.5F * f1;
                float f4 = Mth.cos(f) * 0.5F * f1;
                yoyo.level.addParticle(ParticleTypes.ITEM_SLIME, yoyo.getX() + (double)f2, yoyo.getY() + (double) f4, yoyo.getZ() + (double)f3, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, level, components, tooltipFlag);
        components.add(Component.translatable("moreyoyos.tip3").withStyle(ChatFormatting.GREEN, ChatFormatting.ITALIC));
    }
}
