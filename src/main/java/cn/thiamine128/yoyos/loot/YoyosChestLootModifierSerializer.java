package cn.thiamine128.yoyos.loot;

import com.google.gson.JsonObject;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;

public class YoyosChestLootModifierSerializer extends GlobalLootModifierSerializer<YoyosChestLootModifier> {
    @Override
    public YoyosChestLootModifier read(ResourceLocation location, JsonObject object, LootItemCondition[] ailootcondition) {
        String name = GsonHelper.getAsString(object, "yoyo");
        float chance = GsonHelper.getAsFloat(object, "chance");

        return new YoyosChestLootModifier(ailootcondition, Registry.ITEM.get(new ResourceLocation(name)), chance);
    }

    @Override
    public JsonObject write(YoyosChestLootModifier instance) {
        JsonObject res = this.makeConditions(instance.getConditions());
        res.addProperty("yoyo", instance.getYoyo().getRegistryName().toString());
        res.addProperty("Chance", instance.getChance());
        return res;
    }
}
