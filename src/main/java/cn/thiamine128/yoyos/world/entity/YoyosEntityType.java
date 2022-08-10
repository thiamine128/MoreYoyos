package cn.thiamine128.yoyos.world.entity;

import cn.thiamine128.yoyos.MoreYoyos;
import cn.thiamine128.yoyos.world.entity.projectile.YoyoEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class YoyosEntityType {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, MoreYoyos.MODID);

    public static final RegistryObject<EntityType<YoyoEntity>> YOYO = ENTITY_TYPES.register("yoyo",
            () -> EntityType.Builder.<YoyoEntity>of(YoyoEntity::new, MobCategory.MISC).noSave().noSummon().sized(0.5f, 0.5f).build("yoyo"));
}
