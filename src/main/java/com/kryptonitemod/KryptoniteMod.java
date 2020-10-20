package com.kryptonitemod;

import com.kryptonitemod.init.*;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("kryptonitemod")
public class KryptoniteMod {
    public static final String MOD_ID = "kryptonitemod";
    public static final int TICKS_PER_SECOND = 20;
    public static final ItemGroup CREATIVE_TAB = new ItemGroup("kryptoniteTab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(KryptoniteItems.KRYPTONITE_BLOCK.get());
        }
    };

    public KryptoniteMod() {
        KryptoniteItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        KryptoniteBlocks.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        KryptoniteEntityTypes.ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
        KryptoniteTileEntityTypes.TILE_ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
        KryptoniteContainerTypes.CONTAINER_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());

        MinecraftForge.EVENT_BUS.register(this);
    }
}
