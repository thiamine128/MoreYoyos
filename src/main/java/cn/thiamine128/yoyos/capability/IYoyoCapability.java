package cn.thiamine128.yoyos.capability;

import cn.thiamine128.yoyos.world.entity.projectile.YoyoEntity;

public interface IYoyoCapability {
    YoyoEntity getYoyo();
    void setYoyo(YoyoEntity yoyo);
}
