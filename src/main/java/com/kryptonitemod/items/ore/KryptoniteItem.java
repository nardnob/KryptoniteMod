package com.kryptonitemod.items.ore;

import com.kryptonitemod.KryptoniteMod;
import net.minecraft.item.Item;

public class KryptoniteItem extends Item {
    public static final String NAME = "kryptonite_item";

    public KryptoniteItem() {
        super(new Item.Properties().group(KryptoniteMod.CREATIVE_TAB));
    }
}
