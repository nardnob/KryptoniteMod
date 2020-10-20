package com.kryptonitemod.init;

import com.kryptonitemod.KryptoniteMod;
import com.kryptonitemod.blocks.KryptoniteBlock;
import com.kryptonitemod.blocks.KryptoniteOreBlock;
import com.kryptonitemod.blocks.KryptoniteRefineryBlock;
import com.kryptonitemod.tileentities.KryptoniteRefineryTileEntity;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = KryptoniteMod.modId, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class KryptoniteTileEntityTypes {
    public static final DeferredRegister<TileEntityType<?>> tileEntityTypes = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, KryptoniteMod.modId);

    // We don't have a datafixer for our TileEntities, so we pass null into build.
    public static final RegistryObject<TileEntityType<KryptoniteRefineryTileEntity>> kryptoniteRefineryTileEntity = tileEntityTypes.register(
            KryptoniteRefineryTileEntity.name, () ->
                TileEntityType.Builder
                    .create(KryptoniteRefineryTileEntity::new, KryptoniteBlocks.kryptoniteRefineryBlock.get())
                    .build(null)
    );
}