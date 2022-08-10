package cn.thiamine128.yoyos.event;

import cn.thiamine128.yoyos.world.item.AbstractYoyoItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber()
public class YoyosRenderHandEvent {
    @SubscribeEvent
    public static void onRenderHand(net.minecraftforge.client.event.RenderHandEvent event) {
        ItemStack itemStack = event.getItemStack();
        if (itemStack.getItem() instanceof AbstractYoyoItem) {
            event.getPoseStack().pushPose();
            Player player = Minecraft.getInstance().player;
            ItemInHandRenderer itemInHandRenderer = Minecraft.getInstance().getItemInHandRenderer();
            float f4 = event.getHand() == InteractionHand.MAIN_HAND ? player.getAttackAnim(event.getPartialTicks()) : 0.0F;
            float f5 = 1.0F - Mth.lerp(event.getPartialTicks(), itemInHandRenderer.oMainHandHeight, itemInHandRenderer.mainHandHeight);
            HumanoidArm humanoidArm = (event.getHand() == InteractionHand.MAIN_HAND) ? player.getMainArm() : player.getMainArm().getOpposite();
            Minecraft.getInstance().getItemInHandRenderer().renderPlayerArm(event.getPoseStack(), event.getMultiBufferSource(), event.getPackedLight(), f5, f4, humanoidArm);
            event.getPoseStack().popPose();
        }
    }
}
