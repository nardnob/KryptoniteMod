package com.kryptonitemod.util;

import com.kryptonitemod.util.RegistryHandler;
import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum KryptoniteItemTier implements IItemTier {
    //Harvest Levels
    // 3 - diamond
    // 2 - iron
    // 1 - stone
    // 0 - wood

    //Uses
    // 32   - gold
    // 59   - wood
    // 64   - flint & steel
    // 131  - stone
    // 250  - iron
    // 336  - shield
    // 384  - bow
    // 432  - elytra
    // 1561 - diamond

    //Efficiency
    // 2  - wood
    // 4  - stone
    // 6  - iron
    // 8  - diamond
    // 10 - gold

    //Enchantability
    // 14 - iron tools
    // 9  - iron armor
    // 22 - gold tools
    // 25 - gold armor
    // 10 - diamond tools
    // 10 - diamond armor

    //Damage
    //1 (all items do 1 damage) + base damage (3.0F here) + items damage

    //harvest level, uses, efficiency, base attack damage, enchantability
    KRYPTONITE(3, 1561, 10.0F, 3.0F, 25, () -> { return Ingredient.fromItems(RegistryHandler.KRYPTONITE_SWORD_ITEM.get()); });

    private final int HarvestLevel;
    private final int MaxUses;
    private final float Efficiency;
    private final float AttackDamage;
    private final int Enchantability;
    private final Supplier<Ingredient> RepairMaterial;

    KryptoniteItemTier(int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability, Supplier<Ingredient> repairMaterial) {
        HarvestLevel = harvestLevel;
        MaxUses = maxUses;
        Efficiency = efficiency;
        AttackDamage = attackDamage;
        Enchantability = enchantability;
        RepairMaterial = repairMaterial;
    }

    @Override
    public int getMaxUses() {
        return MaxUses;
    }

    @Override
    public float getEfficiency() {
        return Efficiency;
    }

    @Override
    public float getAttackDamage() {
        return AttackDamage;
    }

    @Override
    public int getHarvestLevel() {
        return HarvestLevel;
    }

    @Override
    public int getEnchantability() {
        return Enchantability;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return RepairMaterial.get();
    }
}
