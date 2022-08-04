package cn.thiamine128.yoyos.world.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class YoyosCreativeModeTab extends CreativeModeTab {
    public YoyosCreativeModeTab() {
        super("yoyos");
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(YoyosItems.BLAZE_YOYO.get());
    }
}
