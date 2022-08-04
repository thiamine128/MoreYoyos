package cn.thiamine128.yoyos.world.item;

import cn.thiamine128.yoyos.world.entity.projectile.YoyoEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class WitherYoyoItem extends SimpleYoyoItem {
    public WitherYoyoItem(int maxDamage, double attackDamage, double stringLength, double speed, float knockback, int attackInterval) {
        super(maxDamage, attackDamage, stringLength, speed, knockback, attackInterval);
    }

    @Override
    public void onHitEntity(YoyoEntity yoyo, Entity entity) {
        super.onHitEntity(yoyo, entity);
        if (yoyo.getOwner() instanceof LivingEntity) {
            RandomSource random = ((LivingEntity) yoyo.getOwner()).getRandom();
            if (random.nextFloat() < 0.3f) {
                ((LivingEntity) yoyo.getOwner()).heal(2);
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, level, components, tooltipFlag);
        components.add(Component.translatable("moreyoyos.tip4").withStyle(ChatFormatting.RED, ChatFormatting.ITALIC));
    }
}
