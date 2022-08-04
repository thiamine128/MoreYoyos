package cn.thiamine128.yoyos.world.damagesource;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.entity.Entity;

public class YoyosDamageSource {
    public static DamageSource yoyo(Entity user) {
        return new EntityDamageSource("yoyo", user);
    }
}
