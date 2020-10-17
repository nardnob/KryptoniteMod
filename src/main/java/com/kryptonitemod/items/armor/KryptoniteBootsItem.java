package com.kryptonitemod.items.armor;

import com.kryptonitemod.KryptoniteMod;
import com.kryptonitemod.util.KryptoniteArmorMaterial;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;

public class KryptoniteBootsItem extends ArmorItem {
    public static final String name = "kryptonite_boots_item";

    public KryptoniteBootsItem() {
        super(KryptoniteArmorMaterial.KRYPTONITE, EquipmentSlotType.FEET, new Properties().group(KryptoniteMod.creativeTab));
    }
}
