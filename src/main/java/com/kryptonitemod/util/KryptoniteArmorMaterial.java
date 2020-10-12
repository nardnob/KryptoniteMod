package com.kryptonitemod.util;

import com.kryptonitemod.KryptoniteMod;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

public enum KryptoniteArmorMaterial implements IArmorMaterial {
    KRYPTONITE(KryptoniteMod.ModId + ":kryptonite", 33, new int[] { 2, 5, 6, 2 }, 22, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,
            3.0F, () -> { return Ingredient.fromItems(RegistryHandler.KRYPTONITE_ITEM.get()); });

    private static final int[] MAX_DAMAGE_ARRAY = new int[] { 11, 16, 15, 13 };
    private final String Name;
    private final int MaxDamageFactor;
    private final int[] DamageReductionAmountArray;
    private final int Enchantability;
    private final SoundEvent ItemSoundEvent;
    private final float Toughness;
    private final Supplier<Ingredient> RepairMaterial;

    //Max damage factor
    // 5  - leather
    // 7  - gold
    // 15 - iron
    // 33 - diamond

    KryptoniteArmorMaterial(String name, int maxDamageFactor, int[] damageReductionAmountArray, int enchantability,
                            SoundEvent itemSoundEvent, float toughness, Supplier<Ingredient> repairMaterial) {
        this.Name = name;
        this.MaxDamageFactor = maxDamageFactor;
        this.DamageReductionAmountArray = damageReductionAmountArray;
        this.Enchantability = enchantability;
        this.ItemSoundEvent = itemSoundEvent;
        this.Toughness = toughness;
        this.RepairMaterial = repairMaterial;
    }

    @Override
    public int getDurability(EquipmentSlotType slotIn) {
        return this.MAX_DAMAGE_ARRAY[slotIn.getIndex()] * this.MaxDamageFactor;
    }

    @Override
    public int getDamageReductionAmount(EquipmentSlotType slotIn) {
        return this.DamageReductionAmountArray[slotIn.getIndex()];
    }

    @Override
    public int getEnchantability() {
        return this.Enchantability;
    }

    @Override
    public net.minecraft.util.SoundEvent getSoundEvent() {
        return this.ItemSoundEvent;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return this.RepairMaterial.get();
    }

    @OnlyIn(Dist.CLIENT) //needed to make sure this property is only accessed client-side?
    @Override
    public String getName() {
        return this.Name;
    }

    @Override
    public float getToughness() {
        return this.Toughness;
    }

    @Override
    public float func_230304_f_() {
        return 0;
    }
}
