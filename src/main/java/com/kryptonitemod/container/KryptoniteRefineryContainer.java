package com.kryptonitemod.container;

import com.kryptonitemod.init.KryptoniteBlocks;
import com.kryptonitemod.init.KryptoniteContainerTypes;
import com.kryptonitemod.tileentities.KryptoniteRefineryTileEntity;
import com.kryptonitemod.util.helpers.FunctionalIntReferenceHolder;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.IntReferenceHolder;
import net.minecraftforge.items.SlotItemHandler;
import javax.annotation.Nonnull;
import java.util.Objects;

/**
 * Charge time is synced with
 * Server: Each tick {@link #detectAndSendChanges()} is called ({@link ServerPlayerEntity#tick()})
 * Server: The (tracked) value of the tile's energy is updated ({@link #updateProgressBar(int, int)})
 * Server: If the value is different from the value last sent to the client ({@link IntReferenceHolder#isDirty()}),
 * it is synced to the client ({@link ServerPlayerEntity#sendWindowProperty(Container, int, int)})
 * Client: The sync packet is received ClientPlayNetHandler#handleWindowProperty(SWindowPropertyPacket)
 * and the tracked value of is updated ({@link Container#updateProgressBar(int, int)})
 * Client: The tile's data is set to the new value
 */
public class KryptoniteRefineryContainer extends Container {
    public static final String NAME = "kryptonite_refinery_container";
    public final KryptoniteRefineryTileEntity tileEntity;
    private final IWorldPosCallable canInteractWithCallable;
    private static final int SLOT_SIZE_WITH_PADDING = 18; // slots are 16x16, plus 2 (for spacing/borders) is 18x18

    /**
     * Logical-client-side constructor, called from ContainerType#create(IContainerFactory)
     * Calls the logical-server-side constructor with the TileEntity at the pos in the PacketBuffer
     */
    public KryptoniteRefineryContainer(final int windowId, final PlayerInventory playerInventory, final PacketBuffer data) {
        this(windowId, playerInventory, getTileEntity(playerInventory, data));
    }

    /**
     * Constructor called logical-server-side from {@link KryptoniteRefineryTileEntity#createMenu}
     * and logical-client-side from {@link #KryptoniteRefineryContainer(int, PlayerInventory, PacketBuffer)}
     */
    public KryptoniteRefineryContainer(final int windowId, final PlayerInventory playerInventory, final KryptoniteRefineryTileEntity tileEntity) {
        super(KryptoniteContainerTypes.KRYPTONITE_REFINERY.get(), windowId);
        this.tileEntity = tileEntity;
        this.canInteractWithCallable = IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos());

        // Add tracking for data (Syncs to client/updates value when it changes)
        this.trackInt(new FunctionalIntReferenceHolder(() -> tileEntity.inputChargeTimeLeft, v -> tileEntity.inputChargeTimeLeft = (short) v));
        this.trackInt(new FunctionalIntReferenceHolder(() -> tileEntity.maxInputChargeTime, v -> tileEntity.maxInputChargeTime = (short) v));
        this.trackInt(new FunctionalIntReferenceHolder(() -> tileEntity.fuelBurnTimeLeft, v -> tileEntity.fuelBurnTimeLeft = (short) v));
        this.trackInt(new FunctionalIntReferenceHolder(() -> tileEntity.maxFuelBurnTime, v -> tileEntity.maxFuelBurnTime = (short) v));

        // Add all the slots for the tileEntity's inventory and the playerInventory to this container


        final int fuelStartX = 8;
        final int fuelStartY = 8;
        // Input slots
        for (int column = 0; column < 9; column++) {
            this.addSlot(new SlotItemHandler(tileEntity.inventory, KryptoniteRefineryTileEntity.FUEL_SLOT_1 + column,
                    fuelStartX + (column * this.SLOT_SIZE_WITH_PADDING), fuelStartY));
        }

        // Tile inventory slot(s)
        this.addSlot(new SlotItemHandler(tileEntity.inventory, KryptoniteRefineryTileEntity.INPUT_SLOT, 80, 60));

        final int playerInventoryStartX = 8;
        final int playerInventoryStartY = 84;

        // Player Top Inventory slots
        for (int row = 0; row < 3; ++row) {
            for (int column = 0; column < 9; ++column) {
                this.addSlot(new Slot(playerInventory, 9 + (row * 9) + column, playerInventoryStartX + (column * SLOT_SIZE_WITH_PADDING), playerInventoryStartY + (row * SLOT_SIZE_WITH_PADDING)));
            }
        }

        final int playerHotbarY = playerInventoryStartY + SLOT_SIZE_WITH_PADDING * 3 + 4;
        // Player Hotbar slots
        for (int column = 0; column < 9; ++column) {
            this.addSlot(new Slot(playerInventory, column, playerInventoryStartX + (column * SLOT_SIZE_WITH_PADDING), playerHotbarY));
        }
    }

    private static KryptoniteRefineryTileEntity getTileEntity(final PlayerInventory playerInventory, final PacketBuffer data) {
        Objects.requireNonNull(playerInventory, "playerInventory cannot be null!");
        Objects.requireNonNull(data, "data cannot be null!");
        final TileEntity tileAtPos = playerInventory.player.world.getTileEntity(data.readBlockPos());
        if (tileAtPos instanceof KryptoniteRefineryTileEntity)
            return (KryptoniteRefineryTileEntity) tileAtPos;
        throw new IllegalStateException("Tile entity is not correct! " + tileAtPos);
    }

    /**
     * Generic & dynamic version of {@link Container#transferStackInSlot(PlayerEntity, int)}.
     * Handle when the stack in slot {@code index} is shift-clicked.
     * Normally this moves the stack between the player inventory and the other inventory(s).
     *
     * @param player the player passed in
     * @param index  the index passed in
     * @return the {@link ItemStack}
     */
    @Nonnull
    @Override
    public ItemStack transferStackInSlot(final PlayerEntity player, final int index) {
        ItemStack returnStack = ItemStack.EMPTY;
        final Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            final ItemStack slotStack = slot.getStack();
            returnStack = slotStack.copy();

            final int containerSlots = this.inventorySlots.size() - player.inventory.mainInventory.size();
            if (index < containerSlots) {
                if (!mergeItemStack(slotStack, containerSlots, this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!mergeItemStack(slotStack, 0, containerSlots, false)) {
                return ItemStack.EMPTY;
            }
            if (slotStack.getCount() == 0) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
            if (slotStack.getCount() == returnStack.getCount()) {
                return ItemStack.EMPTY;
            }
            slot.onTake(player, slotStack);
        }
        return returnStack;
    }

    @Override
    public boolean canInteractWith(@Nonnull final PlayerEntity player) {
        return isWithinUsableDistance(canInteractWithCallable, player, KryptoniteBlocks.KRYPTONITE_REFINERY.get());
    }
}
