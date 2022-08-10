package cn.thiamine128.yoyos.world.item.enchantment;

import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.apache.commons.lang3.mutable.MutableFloat;
import org.apache.commons.lang3.mutable.MutableInt;

import java.util.Map;

public class YoyosEnchantmentHelper extends EnchantmentHelper {
    public static int getSpeedBonus(ItemStack itemStack) {
        MutableInt mutableInt = new MutableInt();
        runIterationOnItem((enchantment, level) -> {
            if (enchantment instanceof SpinningEnchantment)
                mutableInt.add(((SpinningEnchantment) enchantment).getAttackSpeedBonus(level));
        }, itemStack);
        return mutableInt.intValue();
    }

    private static void runIterationOnItem(EnchantmentHelper.EnchantmentVisitor p_44851_, ItemStack p_44852_) {
        if (!p_44852_.isEmpty()) {
            ListTag listtag = p_44852_.getEnchantmentTags();

            for(int i = 0; i < listtag.size(); ++i) {
                CompoundTag compoundtag = listtag.getCompound(i);
                Registry.ENCHANTMENT.getOptional(getEnchantmentId(compoundtag)).ifPresent((p_182437_) -> {
                    p_44851_.accept(p_182437_, getEnchantmentLevel(compoundtag));
                });
            }
        }
    }
}
