package com.kryptonitemod.items.blocks;

import com.kryptonitemod.KryptoniteMod;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;

public class KryptoniteBlockItem extends BlockItem {
    public static final String NAME = "kryptonite_block_item";

    public KryptoniteBlockItem(Block blockIn) {
        super(blockIn, new Properties().group(KryptoniteMod.CREATIVE_TAB));
    }
}