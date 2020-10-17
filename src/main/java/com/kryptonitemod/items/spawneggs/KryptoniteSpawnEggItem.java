package com.kryptonitemod.items.spawneggs;

import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class KryptoniteSpawnEggItem extends SpawnEggItem {
    protected static final List<KryptoniteSpawnEggItem> _unaddedEggs = new ArrayList<>();
    private final Lazy<? extends EntityType<?>> _entityTypeSupplier;

    public KryptoniteSpawnEggItem(final RegistryObject<? extends EntityType<?>> entityTypeSupplier, int primaryColorIn, int secondaryColorIn, Properties builder) {
        super(null, primaryColorIn, secondaryColorIn, builder);
        this._entityTypeSupplier = Lazy.of(entityTypeSupplier::get);
        this._unaddedEggs.add(this);
    }

    public static void initSpawnEggs() {
        //"field_195987_b" - map of existing eggs
        final Map<EntityType<?>, SpawnEggItem> spawnEggs = ObfuscationReflectionHelper.getPrivateValue(SpawnEggItem.class, null, "field_195987_b");

        DefaultDispenseItemBehavior dispenseBehavior = new DefaultDispenseItemBehavior() {
            @Override
            protected ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
                Direction direction = source.getBlockState().get(DispenserBlock.FACING);
                EntityType<?> type = ((SpawnEggItem)stack.getItem()).getType(stack.getTag());
                type.spawn(source.getWorld(), stack, null, source.getBlockPos(), SpawnReason.DISPENSER, direction != Direction.UP, false);
                stack.shrink(1);

                return stack;
            }
        };

        for (final SpawnEggItem spawnEgg : _unaddedEggs) {
            spawnEggs.put(spawnEgg.getType(null), spawnEgg);
            DispenserBlock.registerDispenseBehavior(spawnEgg, dispenseBehavior);
        }

        _unaddedEggs.clear();
    }

    @Override
    public EntityType<?> getType(@Nullable CompoundNBT nbt) {
        return this._entityTypeSupplier.get();
    }
}
