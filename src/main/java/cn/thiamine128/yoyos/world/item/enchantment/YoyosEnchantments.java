package cn.thiamine128.yoyos.world.item.enchantment;

import cn.thiamine128.yoyos.MoreYoyos;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.DamageEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class YoyosEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, MoreYoyos.MODID);

    public static final RegistryObject<SpinningEnchantment> SPINNING = ENCHANTMENTS.register("spinning", () -> new SpinningEnchantment());
    public static final RegistryObject<YoyoDamageEnchantment> SHARPNESS = ENCHANTMENTS.register("sharpness", () -> new YoyoDamageEnchantment(Enchantment.Rarity.COMMON, 0, EquipmentSlot.MAINHAND));
    public static final RegistryObject<YoyoDamageEnchantment> SMITE = ENCHANTMENTS.register("smite", () -> new YoyoDamageEnchantment(Enchantment.Rarity.UNCOMMON, 1, EquipmentSlot.MAINHAND));
    public static final RegistryObject<YoyoDamageEnchantment> BANE_OF_ARTHROPODS = ENCHANTMENTS.register("bane_of_arthropods", () -> new YoyoDamageEnchantment(Enchantment.Rarity.UNCOMMON, 2, EquipmentSlot.MAINHAND));
}
