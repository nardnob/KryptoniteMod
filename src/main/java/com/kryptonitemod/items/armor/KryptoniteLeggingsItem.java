package com.kryptonitemod.items.armor;

import com.kryptonitemod.KryptoniteMod;
import com.kryptonitemod.util.KryptoniteArmorMaterial;
import com.kryptonitemod.util.helpers.IKryptoniteChargeable;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;

public class KryptoniteLeggingsItem extends ArmorItem implements IKryptoniteChargeable {
    public static final String NAME = "kryptonite_leggings_item";

    public KryptoniteLeggingsItem() {
        super(KryptoniteArmorMaterial.KRYPTONITE, EquipmentSlotType.LEGS, new Properties().group(KryptoniteMod.CREATIVE_TAB));
    }
}
