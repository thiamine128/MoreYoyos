package cn.thiamine128.yoyos.world.item;

import cn.thiamine128.yoyos.world.entity.projectile.YoyoEntity;
import net.minecraft.world.entity.Entity;

public class BlazeYoyoItem extends SimpleYoyoItem{
    public BlazeYoyoItem(int maxDamage, double attackDamage, double stringLength, double speed, float knockback, int attackInterval) {
        super(maxDamage, attackDamage, stringLength, speed, knockback, attackInterval);
    }
    @Override
    public double getDamage(YoyoEntity yoyo, Entity victim) {
        if (victim.isOnFire())
            return super.getDamage(yoyo, victim) * 2;
        return super.getDamage(yoyo, victim);
    }
}
