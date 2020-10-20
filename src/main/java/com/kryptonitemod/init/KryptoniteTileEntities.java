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
public class KryptoniteTileEntities {
    @SubscribeEvent
    public static void RegisterTileEntities(RegistryEvent.Register<TileEntityType<?>> evt) {
        TileEntityType<?> type = TileEntityType.Builder.create(KryptoniteRefineryTileEntity::new, null).build(null);
        type.setRegistryName(KryptoniteMod.modId, KryptoniteRefineryTileEntity.name);
        evt.getRegistry().register(type);
    }
}