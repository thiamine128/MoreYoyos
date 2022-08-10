package cn.thiamine128.yoyos.world.item;

import cn.thiamine128.yoyos.capability.IYoyoCapability;
import cn.thiamine128.yoyos.capability.YoyosCapabilities;
import cn.thiamine128.yoyos.world.entity.projectile.YoyoEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FunctionalYoyoItem extends AbstractYoyoItem {
    private int cooldown;
    private int useTime;

    public FunctionalYoyoItem(int maxDamage, double attackDamage, double stringLength, double speed, float knockback, int attackInterval, int cooldown, int useTime) {
        super((new Properties()).tab(YoyosCreativeModeTabs.YOYOS).durability(maxDamage), attackDamage, stringLength, speed, knockback, attackInterval);
        this.cooldown = cooldown;
        this.useTime = useTime;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(itemStack);
    }

    @Override
    public void releaseUsing(ItemStack itemStack, Level level, LivingEntity user, int time) {
        if (!level.isClientSide && user instanceof Player) {
            Player player = (Player) user;
            LazyOptional<IYoyoCapability> cap = player.getCapability(YoyosCapabilities.YOYO);
            cap.ifPresent(c -> {
                if (c.getYoyo() != null) {
                    c.getYoyo().discard();
                }
            });
            YoyoEntity yoyo = new YoyoEntity(level, player, itemStack);
            yoyo.asProjectile();
            yoyo.setXRot(player.getXRot());
            yoyo.setYRot(player.getYRot());
            int useDuration = this.getUseDuration(itemStack) - time;

            yoyo.setPower((float) Math.min(useDuration, useTime) / useTime);
            System.out.println(yoyo.getPower());
            level.addFreshEntity(yoyo);
        }
    }

    @Override
    public ItemStack finishUsingItem(ItemStack p_41409_, Level level, LivingEntity user) {
        if (!level.isClientSide) {
            if (user instanceof Player) {
                Player player = (Player) user;
                LazyOptional<IYoyoCapability> cap = player.getCapability(YoyosCapabilities.YOYO);
                cap.ifPresent(c -> {
                    if (c.getYoyo() != null) {
                        c.getYoyo().discard();
                    }
                });
            }
        }
        return super.finishUsingItem(p_41409_, level, user);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, level, components, tooltipFlag);
        components.add(new TranslatableComponent("moreyoyos.tip1").withStyle(ChatFormatting.BLUE));
    }

    @Override
    public UseAnim getUseAnimation(ItemStack p_41452_) {
        return UseAnim.BOW;
    }

    @Override
    public void onHitEntity(YoyoEntity yoyo, Entity vicim) {

    }

    @Override
    public void onCollideWithEntity(YoyoEntity yoyo, Entity entity) {

    }

    @Override
    public void processEffects(YoyoEntity yoyo) {

    }

    @Override
    public boolean isFunctional() {
        return true;
    }

    @Override
    public void onHit(YoyoEntity yoyo) {

    }

    @Override
    public void onFinsh(YoyoEntity yoyo) {
        if (yoyo.getOwner() instanceof Player) {
            Player player = (Player) yoyo.getOwner();
            player.getCooldowns().addCooldown(this, this.getCooldown());
        }
    }

    public int getCooldown() {
        return this.cooldown;
    }
}
