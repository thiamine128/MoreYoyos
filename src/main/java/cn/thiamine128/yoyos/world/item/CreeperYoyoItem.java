package cn.thiamine128.yoyos.world.item;

import cn.thiamine128.yoyos.world.entity.projectile.YoyoEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Explosion;

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
}
