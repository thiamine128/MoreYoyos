package cn.thiamine128.yoyos.world.item;

import cn.thiamine128.yoyos.world.entity.projectile.YoyoEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BlazeYoyoItem extends SimpleYoyoItem{
    public BlazeYoyoItem(int maxDamage, double attackDamage, double stringLength, double speed, float knockback, int attackInterval) {
        super(maxDamage, attackDamage, stringLength, speed, knockback, attackInterval);
    }
    @Override
    public double getDamage(YoyoEntity yoyo, Entity victim) {
        if (victim.isOnFire())
            return super.getDamage(yoyo, victim) * 2;
        return super.getDamage(yoyo, victim);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, level, components, tooltipFlag);
        components.add(Component.translatable("moreyoyos.tip2").withStyle(ChatFormatting.YELLOW, ChatFormatting.ITALIC));
    }
}
