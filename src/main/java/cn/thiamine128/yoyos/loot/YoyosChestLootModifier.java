package cn.thiamine128.yoyos.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.Registry;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

public class YoyosChestLootModifier extends LootModifier {
    /**
     * Constructs a LootModifier.
     *
     * @param conditionsIn the ILootConditions that need to be matched before the loot is modified.
     */
    private Item yoyo;
    private float chance;

    public static Codec<YoyosChestLootModifier> CODEC = RecordCodecBuilder.create(
            (instance) -> {
                return instance.group(LOOT_CONDITIONS_CODEC.fieldOf("conditions").forGetter(lm -> lm.conditions),
                                Registry.ITEM.byNameCodec().fieldOf("yoyo").forGetter(YoyosChestLootModifier::getYoyo),
                                Codec.FLOAT.fieldOf("chance").forGetter(YoyosChestLootModifier::getChance))
                        .apply(instance, YoyosChestLootModifier::new);
            }
    );


    public YoyosChestLootModifier(LootItemCondition[] lootItemConditions, Item yoyo, Float chance) {
        super(lootItemConditions);
        this.yoyo = yoyo;
        this.chance = chance;
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        if (context.getRandom().nextFloat() < this.getChance()) {
            generatedLoot.add(new ItemStack(this.yoyo, 1));
        }
        return generatedLoot;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }

    public Item getYoyo() {
        return yoyo;
    }

    public void setYoyo(Item yoyo) {
        this.yoyo = yoyo;
    }

    public float getChance() {
        return chance;
    }

    public void setChance(float chance) {
        this.chance = chance;
    }
}
