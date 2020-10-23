package com.kryptonitemod.tileentities;

import com.kryptonitemod.blocks.KryptoniteRefineryBlock;
import com.kryptonitemod.container.KryptoniteRefineryContainer;
import com.kryptonitemod.init.KryptoniteBlocks;
import com.kryptonitemod.init.KryptoniteItems;
import com.kryptonitemod.init.KryptoniteTileEntityTypes;
import com.kryptonitemod.util.KrypLogger;
import com.kryptonitemod.util.helpers.IKryptoniteChargeable;
import com.kryptonitemod.util.helpers.IKryptoniteRefineryFuel;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.RangedWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

public class KryptoniteRefineryTileEntity extends TileEntity implements ITickableTileEntity, INamedContainerProvider {
    public static final String NAME = "kryptonite_refinery_tile_entity";

    public static final int INPUT_SLOT = 0;
    public static final int FUEL_SLOT_1 = 1;
    public static final int FUEL_SLOT_2 = 2;
    public static final int FUEL_SLOT_3 = 3;
    public static final int FUEL_SLOT_4 = 4;
    public static final int FUEL_SLOT_5 = 5;
    public static final int FUEL_SLOT_6 = 6;
    public static final int FUEL_SLOT_7 = 7;
    public static final int FUEL_SLOT_8 = 8;
    public static final int FUEL_SLOT_9 = 9;

    private static final long seed = System.currentTimeMillis();
    private static final Random rand = new Random(seed);

    private static final String INVENTORY_TAG = "inventory";
    private static final String INPUT_CHARGE_TIME_LEFT_TAG = "inputChargeTimeLeft";
    private static final String MAX_INPUT_CHARGE_TIME_TAG = "maxInputChargeTime";
    private static final String FUEL_BURN_TIME_LEFT_TAG = "fuelBurnTimeLeft";
    private static final String MAX_FUEL_BURN_TIME_TAG = "maxFuelBurnTime";

    // Store the capability lazy optionals as fields to keep the amount of objects we use to a minimum
    private final LazyOptional<ItemStackHandler> _inventoryCapabilityExternal = LazyOptional.of(() -> this.inventory);
    // Machines (hoppers, pipes) connected to this furnace's top can only insert/extract items from the input slot
    private final LazyOptional<IItemHandlerModifiable> _inventoryCapabilityExternalUp = LazyOptional.of(() -> new RangedWrapper(this.inventory, INPUT_SLOT, INPUT_SLOT + 1));
    // Machines (hoppers, pipes) connected to this furnace's bottom can only insert/extract items from the output slot
    private final LazyOptional<IItemHandlerModifiable> _inventoryCapabilityExternalDown = LazyOptional.of(() -> new RangedWrapper(this.inventory, INPUT_SLOT, INPUT_SLOT + 1));
    // Machines (hoppers, pipes) connected to this furnace's side can only insert/extract items from the fuel and input slots
    private final LazyOptional<IItemHandlerModifiable> _inventoryCapabilityExternalSides = LazyOptional.of(() -> new RangedWrapper(this.inventory, FUEL_SLOT_1, FUEL_SLOT_1 + 1));

    public short inputChargeTimeLeft = -1;
    public short maxInputChargeTime = -1;
    public short fuelBurnTimeLeft = -1;
    public short maxFuelBurnTime = -1;

    public final ItemStackHandler inventory = new ItemStackHandler(10) {
        @Override
        public boolean isItemValid(final int slot, @Nonnull final ItemStack stack) {
            switch (slot) {
                case INPUT_SLOT:
                    return isValidInput(stack);
                case FUEL_SLOT_1:
                case FUEL_SLOT_2:
                case FUEL_SLOT_3:
                case FUEL_SLOT_4:
                case FUEL_SLOT_5:
                case FUEL_SLOT_6:
                case FUEL_SLOT_7:
                case FUEL_SLOT_8:
                case FUEL_SLOT_9:
                    return isValidFuel(stack);
                default:
                    return false;
            }
        }

        @Override
        protected void onContentsChanged(final int slot) {
            super.onContentsChanged(slot);
            // Mark the tile entity as having changed whenever its inventory changes.
            // "markDirty" tells vanilla that the chunk containing the tile entity has
            // changed and means the game will save the chunk to disk later.
            KryptoniteRefineryTileEntity.this.markDirty();
        }
    };

    public KryptoniteRefineryTileEntity() {
        super(KryptoniteTileEntityTypes.KRYPTONITE_REFINERY.get());
    }

    private boolean isValidFuel(final ItemStack stack) {
        if (stack.isEmpty())
            return false;

        return stack.getItem() == KryptoniteItems.KRYPTONITE.get();
    }

    private boolean isValidInput(final ItemStack input) {
        if (input.isEmpty()) {
            return false;
        }

        return input.getItem() instanceof IKryptoniteChargeable;
    }

    @Override
    public void tick() {
        if (world == null || world.isRemote)
            return;

        boolean wasBurningLastTick = isBurning();
        decrementFuelBurnTimeLeft();

        final ItemStack input = inventory.getStackInSlot(INPUT_SLOT).copy();

        if (isValidInput(input)) {
            if (!isBurning()) {
                tryBurningFuel();
            }

            tryChargingInput(input);
        } else {
            // We have an invalid input stack (somehow)
            inputChargeTimeLeft = maxInputChargeTime = -1;
        }

        if (wasBurningLastTick != isBurning()) {
            updateBlockStateWithBurning(isBurning());
        }
    }

    private class FuelStack {
        ItemStack stack;
        int slot;

        private FuelStack(ItemStack stack, int slot) { this.stack = stack; this.slot = slot;}
    }

    private void decrementFuelBurnTimeLeft() {
        if (this.fuelBurnTimeLeft > 0) {
            this.fuelBurnTimeLeft--;
        }
        if (this.fuelBurnTimeLeft == 0) {
            this.fuelBurnTimeLeft = this.maxFuelBurnTime = -1;
        }
    }

    public boolean isBurning() {
        return this.fuelBurnTimeLeft > 0;
    }

    public boolean isCharging() {
        return this.inputChargeTimeLeft > 0;
    }

    private void tryBurningFuel() {
        FuelStack fuelStack = getFirstNonEmptyFuelStack();

        if (fuelStack != null) {
            final int stackBurnTime = getBurnTime(fuelStack.stack);

            if (stackBurnTime > 0) {
                fuelStack.stack.shrink(1);
                inventory.setStackInSlot(fuelStack.slot, fuelStack.stack);
                fuelBurnTimeLeft = maxFuelBurnTime = (short) stackBurnTime;
                return;
            }
        }

        fuelBurnTimeLeft = maxFuelBurnTime = -1;
    }

    private void tryChargingInput(ItemStack input) {
        if (!isBurning()) {
            // No fuel -> add to charge time left to simulate cooling
            if (inputChargeTimeLeft < maxInputChargeTime) {
                if (this.rand.nextDouble() < 0.1) {
                    inputChargeTimeLeft++;
                }
            }
            return;
        }

        inputChargeTimeLeft--;

        if (!isCharging()) {
            inputChargeTimeLeft = maxInputChargeTime = getChargeTime(input);
        }

        double chargePercentage = (double)inputChargeTimeLeft / maxInputChargeTime;
        if (chargePercentage % 0.25 == 0) {
            //this.world.playSound(null, this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), SoundEvents.BLOCK_BEEHIVE_DROP, SoundCategory.BLOCKS, 1.0F, 1.0F);
        }

        if (inputChargeTimeLeft == maxInputChargeTime) {
            this.world.playSound(null, this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), SoundEvents.BLOCK_BUBBLE_COLUMN_UPWARDS_AMBIENT, SoundCategory.BLOCKS, 0.5F, 1.0F);
        }

        if (inputChargeTimeLeft == 0) { //final tick of charge
            //set attributes on input item as a result

            chargeInput(input);
            this.world.playSound(null, this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), SoundEvents.ITEM_BUCKET_EMPTY_LAVA, SoundCategory.BLOCKS, 0.25F, 1.0F);

            inputChargeTimeLeft = maxInputChargeTime = -1;
            return;
        }
    }

    private void chargeInput(ItemStack input) {
    }

    private FuelStack getFirstNonEmptyFuelStack() {
        //Find the first non-empty fuel slot (right-to-left)
        for (int fuelSlot = FUEL_SLOT_9; fuelSlot >= FUEL_SLOT_1; fuelSlot--) {
            ItemStack fuelStack = inventory.getStackInSlot(fuelSlot).copy();

            if (!fuelStack.isEmpty()) {
                return new FuelStack(fuelStack, fuelSlot);
            }
        }

        return null;
    }

    private void updateBlockStateWithBurning(boolean burning) {
        // "markDirty" tells vanilla that the chunk containing the tile entity has
        // changed and means the game will save the chunk to disk later.
        this.markDirty();

        final BlockState newState = this.getBlockState()
                .with(KryptoniteRefineryBlock.BURNING, burning);

        // Flag 2: Send the change to clients
        world.setBlockState(pos, newState, 2);
    }

    private short getBurnTime(final ItemStack input) {
        return input.getItem() instanceof IKryptoniteRefineryFuel ? ((IKryptoniteRefineryFuel)input.getItem()).getBurnTime() : -1;
    }

    private short getChargeTime(final ItemStack input) {
        return input.getItem() instanceof IKryptoniteChargeable ? ((IKryptoniteChargeable)input.getItem()).getChargeTime() : -1;
    }

    /**
     * Retrieves the Optional handler for the capability requested on the specific side.
     *
     * @param cap  The capability to check
     * @param side The Direction to check from. CAN BE NULL! Null is defined to represent 'internal' or 'self'
     * @return The requested an optional holding the requested capability.
     */
    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull final Capability<T> cap, @Nullable final Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (side == null)
                return _inventoryCapabilityExternal.cast();
            switch (side) {
                case DOWN:
                    return _inventoryCapabilityExternalDown.cast();
                case UP:
                    return _inventoryCapabilityExternalUp.cast();
                case NORTH:
                case SOUTH:
                case WEST:
                case EAST:
                    return _inventoryCapabilityExternalSides.cast();
            }
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        if (world != null && !world.isRemote) {
        }
    }

    /**
     * Read saved data from disk into the tile.
     */
    @Override
    public void read(BlockState state, final CompoundNBT compound) {
        super.read(state, compound);
        this.inventory.deserializeNBT(compound.getCompound(INVENTORY_TAG));
        this.inputChargeTimeLeft = compound.getShort(INPUT_CHARGE_TIME_LEFT_TAG);
        this.maxInputChargeTime = compound.getShort(MAX_INPUT_CHARGE_TIME_TAG);
        this.fuelBurnTimeLeft = compound.getShort(FUEL_BURN_TIME_LEFT_TAG);
        this.maxFuelBurnTime = compound.getShort(MAX_FUEL_BURN_TIME_TAG);
    }

    /**
     * Write data from the tile into a compound tag for saving to disk.
     */
    @Nonnull
    @Override
    public CompoundNBT write(final CompoundNBT compound) {
        super.write(compound);
        compound.put(INVENTORY_TAG, this.inventory.serializeNBT());
        compound.putShort(INPUT_CHARGE_TIME_LEFT_TAG, this.inputChargeTimeLeft);
        compound.putShort(MAX_INPUT_CHARGE_TIME_TAG, this.maxInputChargeTime);
        compound.putShort(FUEL_BURN_TIME_LEFT_TAG, this.fuelBurnTimeLeft);
        compound.putShort(MAX_FUEL_BURN_TIME_TAG, this.maxFuelBurnTime);
        return compound;
    }

    /**
     * Get an NBT compound to sync to the client with SPacketChunkData, used for initial loading of the
     * chunk or when many blocks change at once.
     * This compound comes back to you client-side in {@link #handleUpdateTag}
     * The default implementation ({@link TileEntity#handleUpdateTag}) calls  #writeInternal
     * which doesn't save any of our extra data so we override it to call {@link #write} instead
     */
    @Nonnull
    public CompoundNBT getUpdateTag() {
        return this.write(new CompoundNBT());
    }

    /**
     * Invalidates our tile entity
     */
    @Override
    public void remove() {
        super.remove();
        // We need to invalidate our capability references so that any cached references (by other mods) don't
        // continue to reference our capabilities and try to use them and/or prevent them from being garbage collected
        _inventoryCapabilityExternal.invalidate();
    }

    @Nonnull
    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent(KryptoniteBlocks.KRYPTONITE_REFINERY.get().getTranslationKey());
    }

    /**
     * Called from {@link NetworkHooks#openGui}
     * (which is called from {@link KryptoniteRefineryBlock#onBlockActivated} on the logical server)
     *
     * @return The logical-server-side Container for this TileEntity
     */
    @Nonnull
    @Override
    public Container createMenu(final int windowId, final PlayerInventory inventory, final PlayerEntity player) {
        return new KryptoniteRefineryContainer(windowId, inventory, this);
    }
}