package com.kryptonitemod.blocks;

import com.kryptonitemod.tileentities.KryptoniteRefineryTileEntity;
import com.kryptonitemod.util.KryptoniteLogger;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.FurnaceTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.stream.Stream;

public class KryptoniteRefineryBlock extends AbstractFurnaceBlock {
    public static final String name = "kryptonite_refinery_block";
    private static final DirectionProperty _direction = HorizontalBlock.HORIZONTAL_FACING;

    private static final VoxelShape _voxelShapeNorth = Stream.of(
            Block.makeCuboidShape(6, -2, 13, 7, 6, 14), Block.makeCuboidShape(0, 0, 0, 16, 2, 16),
            Block.makeCuboidShape(1, 2, 1, 2, 14, 2), Block.makeCuboidShape(2, 2, 1, 14, 3, 2),
            Block.makeCuboidShape(13, 3, 1, 14, 4, 2), Block.makeCuboidShape(2, 3, 1, 3, 4, 2),
            Block.makeCuboidShape(2, 13, 1, 14, 14, 2), Block.makeCuboidShape(1, 5, 0, 3, 10, 1),
            Block.makeCuboidShape(15, 3, 0, 16, 6, 1), Block.makeCuboidShape(15, 10, 0, 16, 13, 1),
            Block.makeCuboidShape(13, 12, 1, 14, 13, 2), Block.makeCuboidShape(2, 12, 1, 3, 13, 2),
            Block.makeCuboidShape(14, 2, 1, 15, 14, 2), Block.makeCuboidShape(0, 14, 0, 16, 16, 16),
            Block.makeCuboidShape(15, 2, 1, 16, 14, 16), Block.makeCuboidShape(0, 2, 1, 1, 14, 16),
            Block.makeCuboidShape(1, 2, 15, 15, 14, 16), Block.makeCuboidShape(2, 10.8, 14, 4, 13, 15),
            Block.makeCuboidShape(5, 10.8, 14, 6, 13, 15), Block.makeCuboidShape(7, 11.8, 14, 9, 13, 15),
            Block.makeCuboidShape(10, 10.8, 14, 11, 13, 15), Block.makeCuboidShape(12, 10.8, 14, 14, 13, 15),
            Block.makeCuboidShape(8, 0, 7, 9, 5, 8), Block.makeCuboidShape(8, 0, 9, 9, 6, 10),
            Block.makeCuboidShape(8, 0, 11, 9, 7, 12), Block.makeCuboidShape(8, -1, 13, 9, 8, 14),
            Block.makeCuboidShape(6, -1, 7, 7, 3, 8), Block.makeCuboidShape(6, -1, 9, 7, 4, 10),
            Block.makeCuboidShape(6, -1, 11, 7, 5, 12)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    private static final VoxelShape _voxelShapeEast = Stream.of(
            Block.makeCuboidShape(2, -2, 6, 3, 6, 7), Block.makeCuboidShape(0, 0, 0, 16, 2, 16),
            Block.makeCuboidShape(14, 2, 1, 15, 14, 2), Block.makeCuboidShape(14, 2, 2, 15, 3, 14),
            Block.makeCuboidShape(14, 3, 13, 15, 4, 14), Block.makeCuboidShape(14, 3, 2, 15, 4, 3),
            Block.makeCuboidShape(14, 13, 2, 15, 14, 14), Block.makeCuboidShape(15, 5, 1, 16, 10, 3),
            Block.makeCuboidShape(15, 3, 15, 16, 6, 16), Block.makeCuboidShape(15, 10, 15, 16, 13, 16),
            Block.makeCuboidShape(14, 12, 13, 15, 13, 14), Block.makeCuboidShape(14, 12, 2, 15, 13, 3),
            Block.makeCuboidShape(14, 2, 14, 15, 14, 15), Block.makeCuboidShape(0, 14, 0, 16, 16, 16),
            Block.makeCuboidShape(0, 2, 15, 15, 14, 16), Block.makeCuboidShape(0, 2, 0, 15, 14, 1),
            Block.makeCuboidShape(0, 2, 1, 1, 14, 15), Block.makeCuboidShape(1, 10.8, 2, 2, 13, 4),
            Block.makeCuboidShape(1, 10.8, 5, 2, 13, 6), Block.makeCuboidShape(1, 11.8, 7, 2, 13, 9),
            Block.makeCuboidShape(1, 10.8, 10, 2, 13, 11), Block.makeCuboidShape(1, 10.8, 12, 2, 13, 14),
            Block.makeCuboidShape(8, 0, 8, 9, 5, 9), Block.makeCuboidShape(6, 0, 8, 7, 6, 9),
            Block.makeCuboidShape(4, 0, 8, 5, 7, 9), Block.makeCuboidShape(2, -1, 8, 3, 8, 9),
            Block.makeCuboidShape(8, -1, 6, 9, 3, 7), Block.makeCuboidShape(6, -1, 6, 7, 4, 7),
            Block.makeCuboidShape(4, -1, 6, 5, 5, 7)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    private static final VoxelShape _voxelShapeSouth = Stream.of(
            Block.makeCuboidShape(0, 0, 0, 16, 2, 16), Block.makeCuboidShape(14, 2, 14, 15, 14, 15),
            Block.makeCuboidShape(2, 2, 14, 14, 3, 15), Block.makeCuboidShape(2, 3, 14, 3, 4, 15),
            Block.makeCuboidShape(13, 3, 14, 14, 4, 15), Block.makeCuboidShape(2, 13, 14, 14, 14, 15),
            Block.makeCuboidShape(13, 5, 15, 15, 10, 16), Block.makeCuboidShape(0, 3, 15, 1, 6, 16),
            Block.makeCuboidShape(0, 10, 15, 1, 13, 16), Block.makeCuboidShape(2, 12, 14, 3, 13, 15),
            Block.makeCuboidShape(13, 12, 14, 14, 13, 15), Block.makeCuboidShape(1, 2, 14, 2, 14, 15),
            Block.makeCuboidShape(0, 14, 0, 16, 16, 16), Block.makeCuboidShape(0, 2, 0, 1, 14, 15),
            Block.makeCuboidShape(15, 2, 0, 16, 14, 15), Block.makeCuboidShape(1, 2, 0, 15, 14, 1),
            Block.makeCuboidShape(12, 10.8, 1, 14, 13, 2), Block.makeCuboidShape(10, 10.8, 1, 11, 13, 2),
            Block.makeCuboidShape(7, 11.8, 1, 9, 13, 2), Block.makeCuboidShape(5, 10.8, 1, 6, 13, 2),
            Block.makeCuboidShape(2, 10.8, 1, 4, 13, 2), Block.makeCuboidShape(7, 0, 8, 8, 5, 9),
            Block.makeCuboidShape(7, 0, 6, 8, 6, 7), Block.makeCuboidShape(7, 0, 4, 8, 7, 5),
            Block.makeCuboidShape(7, -1, 2, 8, 8, 3), Block.makeCuboidShape(9, -1, 8, 10, 3, 9),
            Block.makeCuboidShape(9, -1, 6, 10, 4, 7), Block.makeCuboidShape(9, -1, 4, 10, 5, 5),
            Block.makeCuboidShape(9, -2, 2, 10, 6, 3)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    private static final VoxelShape _voxelShapeWest = Stream.of(
            Block.makeCuboidShape(13, -2, 9, 14, 6, 10), Block.makeCuboidShape(0, 0, 0, 16, 2, 16),
            Block.makeCuboidShape(1, 2, 14, 2, 14, 15), Block.makeCuboidShape(1, 2, 2, 2, 3, 14),
            Block.makeCuboidShape(1, 3, 2, 2, 4, 3), Block.makeCuboidShape(1, 3, 13, 2, 4, 14),
            Block.makeCuboidShape(1, 13, 2, 2, 14, 14), Block.makeCuboidShape(0, 5, 13, 1, 10, 15),
            Block.makeCuboidShape(0, 3, 0, 1, 6, 1), Block.makeCuboidShape(0, 10, 0, 1, 13, 1),
            Block.makeCuboidShape(1, 12, 2, 2, 13, 3), Block.makeCuboidShape(1, 12, 13, 2, 13, 14),
            Block.makeCuboidShape(1, 2, 1, 2, 14, 2), Block.makeCuboidShape(0, 14, 0, 16, 16, 16),
            Block.makeCuboidShape(1, 2, 0, 16, 14, 1), Block.makeCuboidShape(1, 2, 15, 16, 14, 16),
            Block.makeCuboidShape(15, 2, 1, 16, 14, 15), Block.makeCuboidShape(14, 10.8, 12, 15, 13, 14),
            Block.makeCuboidShape(14, 10.8, 10, 15, 13, 11), Block.makeCuboidShape(14, 11.8, 7, 15, 13, 9),
            Block.makeCuboidShape(14, 10.8, 5, 15, 13, 6), Block.makeCuboidShape(14, 10.8, 2, 15, 13, 4),
            Block.makeCuboidShape(7, 0, 7, 8, 5, 8), Block.makeCuboidShape(9, 0, 7, 10, 6, 8),
            Block.makeCuboidShape(11, 0, 7, 12, 7, 8), Block.makeCuboidShape(13, -1, 7, 14, 8, 8),
            Block.makeCuboidShape(7, -1, 9, 8, 3, 10), Block.makeCuboidShape(9, -1, 9, 10, 4, 10),
            Block.makeCuboidShape(11, -1, 9, 12, 5, 10)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    public KryptoniteRefineryBlock() {
        super(Properties
            .create(Material.IRON)
            .hardnessAndResistance(5.0f, 6.0f)
            .sound(SoundType.METAL)
            .harvestLevel(0)
            .harvestTool(ToolType.PICKAXE)
        );
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch (state.get(_direction)) {
            case NORTH: return _voxelShapeNorth;
            case EAST: return _voxelShapeEast;
            case SOUTH: return _voxelShapeSouth;
            default: return _voxelShapeWest;
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(_direction, context.getPlacementHorizontalFacing().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(_direction, rot.rotate(state.get(_direction)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(_direction)));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(_direction, LIT);
    }

    @Override
    public float getAmbientOcclusionLightValue(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return 0.6F;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return new KryptoniteRefineryTileEntity();
    }

    /**
     * Interface for handling interaction with blocks that implement AbstractFurnaceBlock. Called in onBlockActivated
     * inside AbstractFurnaceBlock.
     */
    protected void interactWith(World worldIn, BlockPos pos, PlayerEntity player) {
        TileEntity tileentity = worldIn.getTileEntity(pos);
        KryptoniteLogger.logger.info("testkryp1");
        KryptoniteLogger.logger.info(tileentity);
        if (tileentity instanceof KryptoniteRefineryTileEntity) {
            KryptoniteLogger.logger.info("testkryp2");
            player.openContainer((INamedContainerProvider)tileentity);
        }
    }

    /**
     * Called periodically clientside on blocks near the player to show effects (like furnace fire particles).
     */
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        if (stateIn.get(LIT)) {
            double d0 = (double)pos.getX() + 0.5D;
            double d1 = (double)pos.getY();
            double d2 = (double)pos.getZ() + 0.5D;
            if (rand.nextDouble() < 0.1D) {
                worldIn.playSound(d0, d1, d2, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
            }

            Direction direction = stateIn.get(FACING);
            Direction.Axis direction$axis = direction.getAxis();
            double d3 = 0.52D;
            double d4 = rand.nextDouble() * 0.6D - 0.3D;
            double d5 = direction$axis == Direction.Axis.X ? (double)direction.getXOffset() * 0.52D : d4;
            double d6 = rand.nextDouble() * 6.0D / 16.0D;
            double d7 = direction$axis == Direction.Axis.Z ? (double)direction.getZOffset() * 0.52D : d4;
            worldIn.addParticle(ParticleTypes.SMOKE, d0 + d5, d1 + d6, d2 + d7, 0.0D, 0.0D, 0.0D);
            worldIn.addParticle(ParticleTypes.FLAME, d0 + d5, d1 + d6, d2 + d7, 0.0D, 0.0D, 0.0D);
        }
    }
}