package com.kryptonitemod.init;

import com.kryptonitemod.KryptoniteMod;
import com.kryptonitemod.tileentities.KryptoniteRefineryTileEntity;
import com.kryptonitemod.tileentities.KryptoniteFurnaceTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = KryptoniteMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class KryptoniteTileEntityTypes {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, KryptoniteMod.MOD_ID);

    // We don't have a datafixer for our TileEntities, so we pass null into build.
    public static final RegistryObject<TileEntityType<KryptoniteRefineryTileEntity>> KRYPTONITE_REFINERY = TILE_ENTITY_TYPES.register(
            KryptoniteRefineryTileEntity.NAME, () ->
                    TileEntityType.Builder
                            .create(KryptoniteRefineryTileEntity::new, KryptoniteBlocks.KRYPTONITE_REFINERY.get())
                            .build(null)
    );

    // We don't have a datafixer for our TileEntities, so we pass null into build.
    public static final RegistryObject<TileEntityType<KryptoniteFurnaceTileEntity>> KRYPTONITE_FURNACE = TILE_ENTITY_TYPES.register(
            KryptoniteFurnaceTileEntity.NAME, () ->
                TileEntityType.Builder
                    .create(KryptoniteFurnaceTileEntity::new, KryptoniteBlocks.KRYPTONITE_FURNACE.get())
                    .build(null)
    );
}