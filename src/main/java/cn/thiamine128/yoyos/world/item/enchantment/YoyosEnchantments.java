package cn.thiamine128.yoyos.world.item.enchantment;

import cn.thiamine128.yoyos.MoreYoyos;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class YoyosEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, MoreYoyos.MODID);

    public static final RegistryObject<SpinningEnchantment> SPINNING = ENCHANTMENTS.register("spinning", () -> new SpinningEnchantment());
}
