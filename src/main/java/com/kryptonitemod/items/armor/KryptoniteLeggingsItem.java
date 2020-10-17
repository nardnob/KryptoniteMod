package com.kryptonitemod.items.armor;

import com.kryptonitemod.KryptoniteMod;
import com.kryptonitemod.util.KryptoniteArmorMaterial;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;

public class KryptoniteLeggingsItem extends ArmorItem {
    public static final String name = "kryptonite_leggings_item";

    public KryptoniteLeggingsItem() {
        super(KryptoniteArmorMaterial.KRYPTONITE, EquipmentSlotType.LEGS, new Properties().group(KryptoniteMod.creativeTab));
    }
}
