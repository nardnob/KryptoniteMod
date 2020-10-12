package com.kryptonitemod.items.armor;

import com.kryptonitemod.KryptoniteMod;
import com.kryptonitemod.util.KryptoniteArmorMaterial;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;

public class KryptoniteHelmetItem extends ArmorItem {
    public static final String Name = "kryptonite_helmet_item";

    public KryptoniteHelmetItem() {
        super(KryptoniteArmorMaterial.KRYPTONITE, EquipmentSlotType.HEAD, new Item.Properties().group(KryptoniteMod.CreativeTab));
    }
}
