package com.kryptonitemod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class KryptoniteOreBlock extends Block {
    public static final String Name = "kryptonite_ore_block";

    public KryptoniteOreBlock() {
        super(Block.Properties
            .create(Material.IRON)
            .hardnessAndResistance(5.0f, 6.0f)
            .sound(SoundType.STONE)
            .harvestLevel(1)
            .harvestTool(ToolType.PICKAXE)
        );
    }
}
