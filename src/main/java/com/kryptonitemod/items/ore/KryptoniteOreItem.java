package com.kryptonitemod.items.ore;

import com.kryptonitemod.KryptoniteMod;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class KryptoniteOreItem extends BlockItem {
    public static final String NAME = "kryptonite_ore_item";

    public KryptoniteOreItem(Block blockIn) {
        super(blockIn, new Item.Properties().group(KryptoniteMod.CREATIVE_TAB));
    }
}
