package cn.thiamine128.yoyos.world.item;

import cn.thiamine128.yoyos.world.entity.projectile.YoyoEntity;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public abstract class AbstractYoyoItem extends Item {
    private double stringLength;
    private double speed;
    private int attackInterval;
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;

    protected static final UUID BASE_ATTACK_KNOCKBACK_UUID = UUID.fromString("e7753a4d-9910-488e-9911-9b39c52163e4");

    public AbstractYoyoItem(Properties p_41383_, double attackDamage, double stringLength, double speed, float knockback, int attackInterval) {
        super(p_41383_);
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", attackDamage, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_KNOCKBACK, new AttributeModifier(BASE_ATTACK_KNOCKBACK_UUID, "Weapon modifier", knockback, AttributeModifier.Operation.ADDITION));

        this.defaultModifiers = builder.build();
        this.stringLength = stringLength;
        this.speed = speed;
        this.attackInterval = attackInterval;
    }

    @Override
    public int getUseDuration(ItemStack p_41454_) {
        return 20000;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot p_43274_) {
        return p_43274_ == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(p_43274_);
    }

    public abstract void onHitEntity(YoyoEntity yoyo, Entity vicim);

    public abstract void onCollideWithEntity(YoyoEntity yoyo, Entity entity);

    public abstract void processEffects(YoyoEntity yoyo);

    public abstract boolean isFunctional();

    public abstract void onHit(YoyoEntity yoyo);

    public abstract void onFinsh(YoyoEntity yoyo);

    public double getDamage(YoyoEntity yoyo, Entity victim) {
        if (victim instanceof Mob)
            return yoyo.attackDamage + EnchantmentHelper.getDamageBonus(yoyo.getYoyoItem(), ((Mob) victim).getMobType());
        else
            return yoyo.attackDamage;
    }

    public double getStringLength() {
        return this.stringLength;
    }

    public double getSpeed() {
        return this.speed;
    }

    public int getAttackInterval() {
        return this.attackInterval;
    }

    @Override
    public int getEnchantmentValue() {
        return 14;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        super.appendHoverText(itemStack, level, components, flag);
        components.add(Component.translatable("moreyoyos.string_length").append(Component.literal(":" + this.stringLength)).withStyle(ChatFormatting.DARK_GREEN));
        components.add(Component.translatable("moreyoyos.attack_interval").append(Component.literal(":" + this.attackInterval)).withStyle(ChatFormatting.DARK_GREEN));
        components.add(Component.translatable("moreyoyos.speed").append(Component.literal(":" + this.speed)).withStyle(ChatFormatting.DARK_GREEN));
    }
}
