package cn.thiamine128.yoyos;

import cn.thiamine128.yoyos.loot.YoyosGlobalLootModifiers;
import cn.thiamine128.yoyos.world.item.YoyosItems;
import cn.thiamine128.yoyos.world.entity.YoyosEntityType;
import cn.thiamine128.yoyos.world.item.enchantment.YoyosEnchantments;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(MoreYoyos.MODID)
public class MoreYoyos {
    public static final String MODID = "moreyoyos";

    public MoreYoyos() {
        YoyosItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        YoyosEntityType.ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
        YoyosEnchantments.ENCHANTMENTS.register(FMLJavaModLoadingContext.get().getModEventBus());
        YoyosGlobalLootModifiers.GLMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static ResourceLocation loc(String path) {
        return new ResourceLocation(MODID, path);
    }
}
