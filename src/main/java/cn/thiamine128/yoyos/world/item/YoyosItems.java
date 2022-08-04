package cn.thiamine128.yoyos.world.item;

import cn.thiamine128.yoyos.MoreYoyos;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class YoyosItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MoreYoyos.MODID);

    public static final RegistryObject<Item> WOODEN_YOYO = ITEMS.register("wooden_yoyo", () -> new TieredYoyoItem(Tiers.WOOD, 0.0d, 4.0d, 0.5d, 8));
    public static final RegistryObject<Item> STONE_YOYO = ITEMS.register("stone_yoyo", () -> new TieredYoyoItem(Tiers.STONE, 0.0d, 4.0d, 0.5d,8));
    public static final RegistryObject<Item> IRON_YOYO = ITEMS.register("iron_yoyo", () -> new TieredYoyoItem(Tiers.IRON, 0.0d, 4.5d, 0.7d, 8));
    public static final RegistryObject<Item> GOLDEN_YOYO = ITEMS.register("golden_yoyo", () -> new TieredYoyoItem(Tiers.GOLD, 0.0d, 4.5d, 0.8d, 8));
    public static final RegistryObject<Item> DIAMOND_YOYO = ITEMS.register("diamond_yoyo", () -> new TieredYoyoItem(Tiers.DIAMOND, 0.0d, 5.0d, 1.1d, 8));
    public static final RegistryObject<Item> NETHERITE_YOYO = ITEMS.register("netherite_yoyo", () -> new TieredYoyoItem(Tiers.NETHERITE, 0.0d, 6.0d, 1.2d, 8));
    public static final RegistryObject<Item> BLAZE_YOYO = ITEMS.register("blaze_yoyo", () -> new BlazeYoyoItem(127, 3.0d, 5.0d, 0.8d, 0.2f, 8));
    public static final RegistryObject<Item> SLIME_YOYO = ITEMS.register("slime_yoyo", () -> new SlimeYoyoItem(127, 1.0d, 5.0d, 0.8d, 0.0f, 8));
    public static final RegistryObject<Item> WITHER_YOYO = ITEMS.register("wither_yoyo", () -> new WitherYoyoItem(127, 3.0, 5.0d, 0.8d, 0.2f, 8));
    public static final RegistryObject<Item> BEE_YOYO = ITEMS.register("bee_yoyo", () -> new BeeYoyoItem(255, 1.0, 5.0d, 0.8d, 0.1f, 3));
    public static final RegistryObject<Item> SPIDER_YOYO = ITEMS.register("spider_yoyo", () -> new SpiderYoyoItem(127, 2.0, 12.0d, 1.2d, 0.1f, 8));
    public static final RegistryObject<Item> IRON_GOLEM_YOYO = ITEMS.register("iron_golem_yoyo", () -> new IronGolemYoyoItem(2, 14.0, 6.0d, 1.6d, 0.1f, 40));
    public static final RegistryObject<Item> ENDER_YOYO = ITEMS.register("ender_yoyo", () -> new EnderYoyoItem(127, 1.0, 8.0d, 64.0d, 0.1f, 8));
    public static final RegistryObject<Item> CREEPER_YOYO = ITEMS.register("creeper_yoyo", () -> new CreeperYoyoItem(2, 1.0, 6.0d, 1.6d, 0.1f, 40));

    public static void registerLootTables() {

    }
}
