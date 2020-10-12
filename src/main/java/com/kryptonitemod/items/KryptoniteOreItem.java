package com.kryptonitemod.items;

import com.kryptonitemod.KryptoniteMod;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class KryptoniteOreItem extends BlockItem {
    public static final String Name = "kryptonite_ore_item";

    public KryptoniteOreItem(Block blockIn) {
        super(blockIn, new Item.Properties().group(KryptoniteMod.CreativeTab));
    }
}
