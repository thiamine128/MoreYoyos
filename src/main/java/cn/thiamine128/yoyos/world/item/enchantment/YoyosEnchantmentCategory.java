package cn.thiamine128.yoyos.world.item.enchantment;

import cn.thiamine128.yoyos.world.item.AbstractYoyoItem;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class YoyosEnchantmentCategory {
    public static final EnchantmentCategory YOYO = EnchantmentCategory.create("yoyo", (item) -> {
       return item instanceof AbstractYoyoItem;
    });
}
