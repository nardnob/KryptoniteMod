package com.kryptonitemod.items.blocks;

import com.kryptonitemod.KryptoniteMod;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;

public class KryptoniteRefineryItem extends BlockItem {
    public static final String NAME = "kryptonite_refinery_item";
    public static final int REFINERY_CHARGE_TIME = 200;

    public KryptoniteRefineryItem(Block blockIn) {
        super(blockIn, new Properties().group(KryptoniteMod.CREATIVE_TAB));
    }
}
