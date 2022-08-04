package cn.thiamine128.yoyos.world.item.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class SpinningEnchantment extends Enchantment {
    public static final double[] SPEED_BONUS = {0, -1, -2};

    protected SpinningEnchantment() {
        super(Rarity.COMMON, YoyosEnchantmentCategory.YOYO, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMinCost(int level) {
        return 10 * level;
    }

    @Override
    public int getMinLevel() {
        return super.getMinLevel();
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public boolean canEnchant(ItemStack p_44689_) {
        return super.canEnchant(p_44689_);
    }

    public double getAttackSpeedBonus(int level) {
        return SPEED_BONUS[level];
    }
}
