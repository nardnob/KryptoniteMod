package com.kryptonitemod.items.ore;

import com.kryptonitemod.KryptoniteMod;
import com.kryptonitemod.util.helpers.IKryptoniteRefineryFuel;
import net.minecraft.item.Item;

public class KryptoniteItem extends Item implements IKryptoniteRefineryFuel {
    public static final String NAME = "kryptonite_item";

    public KryptoniteItem() {
        super(new Item.Properties().group(KryptoniteMod.CREATIVE_TAB));
    }

    @Override
    public short getBurnTime() {
        return this.DEFAULT_BURN_TIME;
    }
}
