package com.kryptonitemod.items.armor;

import com.kryptonitemod.KryptoniteMod;
import com.kryptonitemod.util.KryptoniteArmorMaterial;
import com.kryptonitemod.util.helpers.IKryptoniteChargeable;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;

public class KryptoniteHelmetItem extends ArmorItem implements IKryptoniteChargeable {
    public static final String NAME = "kryptonite_helmet_item";

    public KryptoniteHelmetItem() {
        super(KryptoniteArmorMaterial.KRYPTONITE, EquipmentSlotType.HEAD, new Item.Properties().group(KryptoniteMod.CREATIVE_TAB));
    }

    @Override
    public short getChargeTime() {
        return this.DEFAULT_CHARGE_TIME;
    }
}
