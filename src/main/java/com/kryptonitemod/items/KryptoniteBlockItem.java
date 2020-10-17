package com.kryptonitemod.items;

import com.kryptonitemod.KryptoniteMod;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;

public class KryptoniteBlockItem extends BlockItem {
    public static final String name = "kryptonite_block_item";

    public KryptoniteBlockItem(Block blockIn) {
        super(blockIn, new Properties().group(KryptoniteMod.creativeTab));
    }
}
