package cn.thiamine128.yoyos.setup;

import cn.thiamine128.yoyos.MoreYoyos;
import cn.thiamine128.yoyos.client.renderer.entity.YoyoRenderer;
import cn.thiamine128.yoyos.world.item.YoyosItems;
import cn.thiamine128.yoyos.world.entity.YoyosEntityType;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {
    @SubscribeEvent
    public static void setupClient(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ItemProperties.register(YoyosItems.WOODEN_YOYO.get(), MoreYoyos.loc("using"), (itemStack, clientLevel, player, i) -> {
                return player != null && player.isUsingItem() && player.getUseItem() == itemStack ? 1.0f : 0.0f;
            });
            ItemProperties.register(YoyosItems.STONE_YOYO.get(), MoreYoyos.loc("using"), (itemStack, clientLevel, player, i) -> {
                return player != null && player.isUsingItem() && player.getUseItem() == itemStack ? 1.0f : 0.0f;
            });
            ItemProperties.register(YoyosItems.IRON_YOYO.get(), MoreYoyos.loc("using"), (itemStack, clientLevel, player, i) -> {
                return player != null && player.isUsingItem() && player.getUseItem() == itemStack ? 1.0f : 0.0f;
            });
            ItemProperties.register(YoyosItems.GOLDEN_YOYO.get(), MoreYoyos.loc("using"), (itemStack, clientLevel, player, i) -> {
                return player != null && player.isUsingItem() && player.getUseItem() == itemStack ? 1.0f : 0.0f;
            });
            ItemProperties.register(YoyosItems.DIAMOND_YOYO.get(), MoreYoyos.loc("using"), (itemStack, clientLevel, player, i) -> {
                return player != null && player.isUsingItem() && player.getUseItem() == itemStack ? 1.0f : 0.0f;
            });
            ItemProperties.register(YoyosItems.NETHERITE_YOYO.get(), MoreYoyos.loc("using"), (itemStack, clientLevel, player, i) -> {
                return player != null && player.isUsingItem() && player.getUseItem() == itemStack ? 1.0f : 0.0f;
            });
            ItemProperties.register(YoyosItems.BLAZE_YOYO.get(), MoreYoyos.loc("using"), (itemStack, clientLevel, player, i) -> {
                return player != null && player.isUsingItem() && player.getUseItem() == itemStack ? 1.0f : 0.0f;
            });
            ItemProperties.register(YoyosItems.SLIME_YOYO.get(), MoreYoyos.loc("using"), (itemStack, clientLevel, player, i) -> {
                return player != null && player.isUsingItem() && player.getUseItem() == itemStack ? 1.0f : 0.0f;
            });
            ItemProperties.register(YoyosItems.WITHER_YOYO.get(), MoreYoyos.loc("using"), (itemStack, clientLevel, player, i) -> {
                return player != null && player.isUsingItem() && player.getUseItem() == itemStack ? 1.0f : 0.0f;
            });
            ItemProperties.register(YoyosItems.BEE_YOYO.get(), MoreYoyos.loc("using"), (itemStack, clientLevel, player, i) -> {
                return player != null && player.isUsingItem() && player.getUseItem() == itemStack ? 1.0f : 0.0f;
            });
            ItemProperties.register(YoyosItems.SPIDER_YOYO.get(), MoreYoyos.loc("using"), (itemStack, clientLevel, player, i) -> {
                return player != null && player.isUsingItem() && player.getUseItem() == itemStack ? 1.0f : 0.0f;
            });
            ItemProperties.register(YoyosItems.ENDER_YOYO.get(), MoreYoyos.loc("using"), (itemStack, clientLevel, player, i) -> {
                return player != null && player.isUsingItem() && player.getUseItem() == itemStack ? 1.0f : 0.0f;
            });
        });
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(YoyosEntityType.YOYO.get(), context -> new YoyoRenderer(context));
    }
}
