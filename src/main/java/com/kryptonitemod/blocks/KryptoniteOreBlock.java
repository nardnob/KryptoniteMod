package com.kryptonitemod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.OreBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.ToolType;

public class KryptoniteOreBlock extends OreBlock {
    public static final String name = "kryptonite_ore_block";

    public KryptoniteOreBlock() {
        super(Block.Properties
            .create(Material.IRON)
            .hardnessAndResistance(5.0f, 6.0f)
            .sound(SoundType.STONE)
            .harvestLevel(1)
            .harvestTool(ToolType.PICKAXE)
        );
    }

    @Override
    public int getExpDrop(BlockState blockState, IWorldReader worldReader, BlockPos position, int fortune, int silkTouch) {
        return 2;
    }
}
