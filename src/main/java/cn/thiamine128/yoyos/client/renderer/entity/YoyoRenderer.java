package cn.thiamine128.yoyos.client.renderer.entity;

import cn.thiamine128.yoyos.world.entity.projectile.YoyoEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public class YoyoRenderer extends EntityRenderer<YoyoEntity> {
    private final ItemRenderer itemRenderer;

    public YoyoRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.itemRenderer = context.getItemRenderer();
    }

    @Override
    public void render(YoyoEntity yoyo, float yaw, float tickDelta, PoseStack poseStack, MultiBufferSource bufferSource, int light) {
        poseStack.pushPose();
        poseStack.pushPose();
        poseStack.translate(0d, 0.4d, 0d);
        poseStack.scale(1.5f, 1.5f, 1.5f);
        poseStack.mulPose(Vector3f.YP.rotationDegrees(-yaw));
        poseStack.mulPose(Vector3f.XP.rotationDegrees((yoyo.life + tickDelta) * 48.0f));
        BakedModel bakedModel = this.itemRenderer.getModel(yoyo.getYoyoItem(), yoyo.level, null, yoyo.getId());
        itemRenderer.render(yoyo.getYoyoItem(), ItemTransforms.TransformType.NONE, false, poseStack, bufferSource, light, OverlayTexture.NO_OVERLAY, bakedModel);
        poseStack.popPose();


        Entity owner = yoyo.getOwner();
        if (owner instanceof Player) {
            Player player = (Player) owner;

            float f = player.getAttackAnim(tickDelta);
            float f1 = Mth.sin(Mth.sqrt(f) * (float)Math.PI);
            float f2 = Mth.lerp(tickDelta, player.yBodyRotO, player.yBodyRot) * ((float)Math.PI / 180F);
            double d0 = (double)Mth.sin(f2);
            double d1 = (double)Mth.cos(f2);
            double d2 = (double) 0.35D;

            double handX;
            double handY;
            double handZ;

            float eyeHeight;

            if ((this.entityRenderDispatcher.options == null || this.entityRenderDispatcher.options.getCameraType().isFirstPerson()) && player == Minecraft.getInstance().player) {
                double d7 = 960.0D / (double)this.entityRenderDispatcher.options.fov().get().intValue();
                Vec3 vec3 = this.entityRenderDispatcher.camera.getNearPlane().getPointOnPlane(0.525F, -1.0F);
                vec3 = vec3.scale(d7);
                vec3 = vec3.yRot(f1 * 0.5F);
                vec3 = vec3.xRot(-f1 * 0.7F);
                handX = Mth.lerp(tickDelta, player.xo, player.getX()) + vec3.x;
                handY = Mth.lerp(tickDelta, player.yo, player.getY()) + vec3.y;
                handZ = Mth.lerp(tickDelta, player.zo, player.getZ()) + vec3.z;
                eyeHeight = player.getEyeHeight();
            } else {
                handX = Mth.lerp(tickDelta, player.xo, player.getX()) - d1 * d2 - d0 * 0.3D;
                handY = player.yo + (double)player.getEyeHeight() + (player.getY() - player.yo) * tickDelta - 1.0D;
                handZ = Mth.lerp(tickDelta, player.zo, player.getZ()) - d0 * d2 + d1 * 0.3D;
                eyeHeight = player.isCrouching() ? -0.1875F : 0.0F;
            }

            double yoyoX = Mth.lerp(tickDelta, yoyo.xo, yoyo.getX());
            double yoyoY = Mth.lerp(tickDelta, yoyo.yo, yoyo.getY());
            double yoyoZ = Mth.lerp(tickDelta, yoyo.zo, yoyo.getZ());

            VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.lineStrip());
            PoseStack.Pose pose = poseStack.last();

            float f4 = (float)(handX - yoyoX);
            float f5 = (float)(handY - yoyoY) + eyeHeight;
            float f6 = (float)(handZ - yoyoZ);

            for(int k = 0; k <= 16; ++k) {
                stringVertex(f4, f5, f6, vertexConsumer, pose, fraction(k, 16), fraction(k + 1, 16));
            }


        }
        poseStack.popPose();
        super.render(yoyo, yaw, tickDelta, poseStack, bufferSource, light);
    }

    @Override
    public ResourceLocation getTextureLocation(YoyoEntity p_114482_) {
        return TextureAtlas.LOCATION_BLOCKS;
    }

    private static float fraction(int p_114691_, int p_114692_) {
        return (float)p_114691_ / (float)p_114692_;
    }

    private static void stringVertex(float p_174119_, float p_174120_, float p_174121_, VertexConsumer p_174122_, PoseStack.Pose p_174123_, float p_174124_, float p_174125_) {
        float f = p_174119_ * p_174124_;
        float f1 = p_174120_ * (p_174124_ * p_174124_ + p_174124_) * 0.5F + 0.25F;
        float f2 = p_174121_ * p_174124_;
        float f3 = p_174119_ * p_174125_ - f;
        float f4 = p_174120_ * (p_174125_ * p_174125_ + p_174125_) * 0.5F + 0.25F - f1;
        float f5 = p_174121_ * p_174125_ - f2;
        float f6 = Mth.sqrt(f3 * f3 + f4 * f4 + f5 * f5);
        f3 /= f6;
        f4 /= f6;
        f5 /= f6;
        p_174122_.vertex(p_174123_.pose(), f, f1, f2).color(127, 127, 127, 255).normal(p_174123_.normal(), f3, f4, f5).endVertex();
    }
}
