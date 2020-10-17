package com.kryptonitemod.util;

import com.kryptonitemod.KryptoniteMod;
import com.kryptonitemod.init.KryptoniteItems;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

public enum KryptoniteArmorMaterial implements IArmorMaterial {
    KRYPTONITE(KryptoniteMod.modId + ":kryptonite", 33, new int[] { 2, 5, 6, 2 }, 22, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,
            3.0F, () -> { return Ingredient.fromItems(KryptoniteItems.kryptoniteItem.get()); });

    private static final int[] _maxDamageArray = new int[] { 11, 16, 15, 13 };
    private final String _name;
    private final int _maxDamageFactor;
    private final int[] _damageReductionAmountArray;
    private final int _enchantability;
    private final SoundEvent _itemSoundEvent;
    private final float _toughness;
    private final Supplier<Ingredient> _repairMaterial;

    //Max damage factor
    // 5  - leather
    // 7  - gold
    // 15 - iron
    // 33 - diamond

    KryptoniteArmorMaterial(String name, int maxDamageFactor, int[] damageReductionAmountArray, int enchantability,
                            SoundEvent itemSoundEvent, float toughness, Supplier<Ingredient> repairMaterial) {
        this._name = name;
        this._maxDamageFactor = maxDamageFactor;
        this._damageReductionAmountArray = damageReductionAmountArray;
        this._enchantability = enchantability;
        this._itemSoundEvent = itemSoundEvent;
        this._toughness = toughness;
        this._repairMaterial = repairMaterial;
    }

    @Override
    public int getDurability(EquipmentSlotType slotIn) {
        return this._maxDamageArray[slotIn.getIndex()] * this._maxDamageFactor;
    }

    @Override
    public int getDamageReductionAmount(EquipmentSlotType slotIn) {
        return this._damageReductionAmountArray[slotIn.getIndex()];
    }

    @Override
    public int getEnchantability() {
        return this._enchantability;
    }

    @Override
    public net.minecraft.util.SoundEvent getSoundEvent() {
        return this._itemSoundEvent;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return this._repairMaterial.get();
    }

    @OnlyIn(Dist.CLIENT) //needed to make sure this property is only accessed client-side?
    @Override
    public String getName() {
        return this._name;
    }

    @Override
    public float getToughness() {
        return this._toughness;
    }

    @Override
    public float getKnockbackResistance() { return 0; }
}
