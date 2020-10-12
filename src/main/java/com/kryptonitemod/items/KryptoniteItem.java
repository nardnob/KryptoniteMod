package com.kryptonitemod.items;

import com.kryptonitemod.KryptoniteMod;
import net.minecraft.item.Item;

public class KryptoniteItem extends Item {
    public static final String Name = "kryptonite_item";

    public KryptoniteItem() {
        super(new Item.Properties().group(KryptoniteMod.CreativeTab));
    }
}
