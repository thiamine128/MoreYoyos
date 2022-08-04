package cn.thiamine128.yoyos.world.item;

import cn.thiamine128.yoyos.world.entity.projectile.YoyoEntity;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class WitherYoyoItem extends SimpleYoyoItem {
    public WitherYoyoItem(int maxDamage, double attackDamage, double stringLength, double speed, float knockback, int attackInterval) {
        super(maxDamage, attackDamage, stringLength, speed, knockback, attackInterval);
    }

    @Override
    public void onHitEntity(YoyoEntity yoyo, Entity entity) {
        super.onHitEntity(yoyo, entity);
        if (yoyo.getOwner() instanceof LivingEntity) {
            RandomSource random = ((LivingEntity) yoyo.getOwner()).getRandom();
            if (random.nextFloat() < 0.3f) {
                ((LivingEntity) yoyo.getOwner()).heal(2);
            }
        }
    }
}
