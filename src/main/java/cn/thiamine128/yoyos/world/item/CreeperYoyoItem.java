package cn.thiamine128.yoyos.world.item;

import cn.thiamine128.yoyos.world.entity.projectile.YoyoEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CreeperYoyoItem extends FunctionalYoyoItem {
    public CreeperYoyoItem(int maxDamage, double attackDamage, double stringLength, double speed, float knockback, int attackInterval) {
        super(maxDamage, attackDamage, stringLength, speed, knockback, attackInterval, 300, 80);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack p_41452_) {
        return UseAnim.BOW;
    }

    @Override
    public void onHit(YoyoEntity yoyo) {
        super.onHit(yoyo);
        Explosion.BlockInteraction explosion$blockinteraction = Explosion.BlockInteraction.NONE;
        yoyo.level.explode(yoyo.getOwner(), yoyo.getX(), yoyo.getY(), yoyo.getZ(), (float) (2.5f * yoyo.getPower()), explosion$blockinteraction);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, level, components, tooltipFlag);
        components.add(Component.translatable("moreyoyos.tip5").withStyle(ChatFormatting.GREEN, ChatFormatting.ITALIC));
    }
}
