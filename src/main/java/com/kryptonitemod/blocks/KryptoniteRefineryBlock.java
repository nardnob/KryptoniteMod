package com.kryptonitemod.blocks;

import com.kryptonitemod.init.KryptoniteTileEntityTypes;
import com.kryptonitemod.tileentities.KryptoniteRefineryTileEntity;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.stream.Stream;

public class KryptoniteRefineryBlock extends HorizontalBlock {
    public static final String NAME = "kryptonite_refinery_block";
    public static final BooleanProperty BURNING = BooleanProperty.create("burning");

    private static final VoxelShape VOXEL_SHAPE_NORTH = Stream.of(
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

    private static final VoxelShape VOXEL_SHAPE_EAST = Stream.of(
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

    private static final VoxelShape VOXEL_SHAPE_SOUTH = Stream.of(
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

    private static final VoxelShape VOXEL_SHAPE_WEST = Stream.of(
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

        // Set the default values for our blockstate properties
        this.setDefaultState(this.getDefaultState()
                .with(HORIZONTAL_FACING, Direction.NORTH)
                .with(BURNING, false)
        );
    }

    @Override
    public boolean hasTileEntity(final BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(final BlockState state, final IBlockReader world) {
        // Always use TileEntityType#create to allow registry overrides to work.
        return KryptoniteTileEntityTypes.KRYPTONITE_REFINERY.get().create();
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch (state.get(HORIZONTAL_FACING)) {
            case NORTH: return VOXEL_SHAPE_NORTH;
            case EAST: return VOXEL_SHAPE_EAST;
            case SOUTH: return VOXEL_SHAPE_SOUTH;
            default: return VOXEL_SHAPE_WEST;
        }
    }

    /**
     * Makes the block face the player when placed
     */
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(HORIZONTAL_FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    /**
     * Returns the blockstate with the given rotation from the passed blockstate.
     * If inapplicable, returns the passed blockstate.
     *
     * @deprecated call via {@link BlockState#rotate(Rotation)} whenever possible. Implementing/overriding is fine.
     */
    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(HORIZONTAL_FACING, rot.rotate(state.get(HORIZONTAL_FACING)));
    }

    /**
     * Returns the blockstate with the given mirror of the passed blockstate.
     * If inapplicable, returns the passed blockstate.
     *
     * @deprecated call via {@link BlockState#mirror(Mirror)} whenever possible. Implementing/overriding is fine.
     */
    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(HORIZONTAL_FACING)));
    }

    /**
     * Called on the logical server when a BlockState with a TileEntity is replaced by another BlockState.
     * We use this method to drop all the items from our tile entity's inventory and update comparators near our block.
     *
     * @deprecated Call via {@link BlockState#onReplaced(World, BlockPos, BlockState, boolean)}
     * Implementing/overriding is fine.
     */
    @Override
    public void onReplaced(BlockState oldState, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (oldState.getBlock() != newState.getBlock()) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if (tileEntity instanceof KryptoniteRefineryTileEntity) {
                final ItemStackHandler inventory = ((KryptoniteRefineryTileEntity) tileEntity).inventory;
                for (int slot = 0; slot < inventory.getSlots(); ++slot)
                    InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), inventory.getStackInSlot(slot));
            }
        }
        super.onReplaced(oldState, worldIn, pos, newState, isMoving);
    }

    /**
     * Called from inside the constructor {@link Block#Block(Properties)} to add all the properties to our blockstate
     */
    @Override
    protected void fillStateContainer(final StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(HORIZONTAL_FACING);
        builder.add(BURNING);
    }

    @Override
    public float getAmbientOcclusionLightValue(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return 0.6F;
    }

    /**
     * Amount of light emitted
     *
     * @deprecated Call via {@link BlockState#getLightValue())}
     * Implementing/overriding is fine.
     */
    public int getLightValue(BlockState state, final World worldIn, final BlockPos pos) {
        return state.get(BURNING) ? super.getLightValue(state, worldIn, pos) : 0;
    }


    /**
     * Called when a player right clicks our block.
     * We use this method to open our gui.
     *
     * @deprecated Call via BlockState#onBlockActivated(World, PlayerEntity, Hand, BlockRayTraceResult) whenever possible.
     * Implementing/overriding is fine.
     */
    @Override
    public ActionResultType onBlockActivated(final BlockState state, final World worldIn, final BlockPos pos,
                                             final PlayerEntity player, final Hand handIn, final BlockRayTraceResult hit) {
        super.onBlockActivated(state, worldIn, pos, player, handIn, hit);

        if (!worldIn.isRemote) {
            final TileEntity tileEntity = worldIn.getTileEntity(pos);
            if (tileEntity instanceof KryptoniteRefineryTileEntity)
                NetworkHooks.openGui((ServerPlayerEntity) player, (KryptoniteRefineryTileEntity) tileEntity, pos);
        }
        return ActionResultType.SUCCESS;
    }

    /**
     * Called periodically clientside on blocks near the player to show effects (like furnace fire particles).
     */
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        super.animateTick(stateIn, worldIn, pos, rand);

        if (stateIn.get(BURNING)) {
            final TileEntity tileEntityAtPos = worldIn.getTileEntity(pos);
            if (tileEntityAtPos instanceof KryptoniteRefineryTileEntity) {
                double blockX = pos.getX();
                double blockY = pos.getY();
                double blockZ = pos.getZ();

                /*
                Direction direction = stateIn.get(HORIZONTAL_FACING);
                Direction.Axis direction$axis = direction.getAxis();
                double randomOffset = rand.nextDouble() * 0.5D;
                double offsetX = direction$axis == Direction.Axis.X ? (double)direction.getXOffset() * 0.5D : randomOffset;
                double offsetY = rand.nextDouble() * 0.5D;
                double offsetZ = direction$axis == Direction.Axis.Z ? (double)direction.getZOffset() * 0.5D : randomOffset;
                worldIn.addParticle(ParticleTypes.FIREWORK, blockX + offsetX, blockY + offsetY, blockZ + offsetZ, 0.0D, 0.0D, 0.0D);
                */

                double offsetX = rand.nextDouble() * 0.6D + 0.2D; //x: [0.2D, 0.8D]
                double offsetY = 0.9D; //y: 0.9D
                double offsetZ = rand.nextDouble() * 0.6D + 0.2D; //z: [0.2D, 0.8D]
                worldIn.addParticle(ParticleTypes.FIREWORK, blockX + offsetX, blockY + offsetY, blockZ + offsetZ, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    /**
     * We return the redstone calculated from our inventory
     *
     * @deprecated call via {@link BlockState#getComparatorInputOverride(World, BlockPos)} whenever possible.
     * Implementing/overriding is fine.
     */
    @Override
    public int getComparatorInputOverride(BlockState blockState, World worldIn, BlockPos pos) {
        final TileEntity tileEntity = worldIn.getTileEntity(pos);
        if (tileEntity instanceof KryptoniteRefineryTileEntity)
            return ItemHandlerHelper.calcRedstoneFromInventory(((KryptoniteRefineryTileEntity) tileEntity).inventory);
        return super.getComparatorInputOverride(blockState, worldIn, pos);
    }
}