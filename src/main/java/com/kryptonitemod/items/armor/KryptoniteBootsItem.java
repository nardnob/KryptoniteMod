package com.kryptonitemod.items.armor;

import com.kryptonitemod.KryptoniteMod;
import com.kryptonitemod.util.KryptoniteArmorMaterial;
import com.kryptonitemod.util.helpers.IKryptoniteChargeable;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;

public class KryptoniteBootsItem extends ArmorItem implements IKryptoniteChargeable {
    public static final String NAME = "kryptonite_boots_item";

    public KryptoniteBootsItem() {
        super(KryptoniteArmorMaterial.KRYPTONITE, EquipmentSlotType.FEET, new Properties().group(KryptoniteMod.CREATIVE_TAB));
    }

    @Override
    public short getChargeTime() {
        return this.DEFAULT_CHARGE_TIME;
    }
}
