package com.kryptonitemod.items.armor;

import com.kryptonitemod.KryptoniteMod;
import com.kryptonitemod.util.KryptoniteArmorMaterial;
import com.kryptonitemod.util.helpers.IKryptoniteChargeable;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;

public class KryptoniteChestplateItem extends ArmorItem implements IKryptoniteChargeable {
    public static final String NAME = "kryptonite_chestplate_item";

    public KryptoniteChestplateItem() {
        super(KryptoniteArmorMaterial.KRYPTONITE, EquipmentSlotType.CHEST, new Properties().group(KryptoniteMod.CREATIVE_TAB));
    }
}
