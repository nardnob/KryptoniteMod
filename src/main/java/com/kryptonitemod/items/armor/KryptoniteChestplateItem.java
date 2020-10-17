package com.kryptonitemod.items.armor;

import com.kryptonitemod.KryptoniteMod;
import com.kryptonitemod.util.KryptoniteArmorMaterial;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;

public class KryptoniteChestplateItem extends ArmorItem {
    public static final String name = "kryptonite_chestplate_item";

    public KryptoniteChestplateItem() {
        super(KryptoniteArmorMaterial.KRYPTONITE, EquipmentSlotType.CHEST, new Properties().group(KryptoniteMod.creativeTab));
    }
}
