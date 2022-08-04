package cn.thiamine128.yoyos.world.item;

import cn.thiamine128.yoyos.world.entity.projectile.YoyoEntity;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.Mth;

public class EnderYoyoItem extends SimpleYoyoItem {
    public EnderYoyoItem(int maxDamage, double attackDamage, double stringLength, double speed, float knockback, int attackInterval) {
        super(maxDamage, attackDamage, stringLength, speed, knockback, attackInterval);
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
                yoyo.level.addParticle(ParticleTypes.PORTAL, yoyo.getX() + (double)f2, yoyo.getY() + (double) f4, yoyo.getZ() + (double)f3, 0.0D, 0.0D, 0.0D);
            }
        }
    }
}
