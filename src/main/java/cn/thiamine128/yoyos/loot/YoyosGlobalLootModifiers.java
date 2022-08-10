package cn.thiamine128.yoyos.loot;

import cn.thiamine128.yoyos.MoreYoyos;
import com.mojang.serialization.Codec;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class YoyosGlobalLootModifiers {
    public static final DeferredRegister GLMS = DeferredRegister.create(ForgeRegistries.Keys.LOOT_MODIFIER_SERIALIZERS, MoreYoyos.MODID);

    public static final RegistryObject<Codec<YoyosChestLootModifier>> YOYOS_CHEST_MODIFER = GLMS.register("chest_loot_modifier", () -> new YoyosChestLootModifierSerializer());
}
