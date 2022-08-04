package cn.thiamine128.yoyos.world.item;

import cn.thiamine128.yoyos.world.entity.projectile.YoyoEntity;

import net.minecraft.world.entity.Entity;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;


public class TieredYoyoItem extends SimpleYoyoItem {
    private final Tier tier;

    public TieredYoyoItem(Tier tier, double attackDamage, double stringLength, double speed, int attackInterval) {
        super(tier.getUses(), attackDamage + tier.getAttackDamageBonus(), stringLength, speed, 0.2f, attackInterval);
        this.tier = tier;
    }

    @Override
    public void onHitEntity(YoyoEntity yoyo, Entity entity) {
        super.onHitEntity(yoyo, entity);
    }

    @Override
    public boolean isValidRepairItem(ItemStack p_43311_, ItemStack p_43312_) {
        return this.tier.getRepairIngredient().test(p_43312_) || super.isValidRepairItem(p_43311_, p_43312_);
    }

    @Override
    public int getEnchantmentValue() {
        return this.tier.getEnchantmentValue();
    }

}
