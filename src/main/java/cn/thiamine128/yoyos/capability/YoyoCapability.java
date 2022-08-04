package cn.thiamine128.yoyos.capability;

import cn.thiamine128.yoyos.MoreYoyos;
import cn.thiamine128.yoyos.world.entity.projectile.YoyoEntity;
import net.minecraft.resources.ResourceLocation;

public class YoyoCapability implements IYoyoCapability {
    public YoyoEntity yoyo;

    public static final ResourceLocation ID = MoreYoyos.loc("yoyo");

    @Override
    public YoyoEntity getYoyo() {
        return this.yoyo;
    }

    @Override
    public void setYoyo(YoyoEntity yoyo) {
        this.yoyo = yoyo;
    }
}
