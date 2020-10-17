package com.kryptonitemod;

import com.kryptonitemod.entities.GorillaEntity;
import com.kryptonitemod.init.KryptoniteBlocks;
import com.kryptonitemod.init.KryptoniteEntityTypes;
import com.kryptonitemod.init.KryptoniteItems;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("kryptonitemod")
public class KryptoniteMod {
    public static final String modId = "kryptonitemod";
    public static final int ticksPerSecond = 20;
    public static final ItemGroup creativeTab = new ItemGroup("kryptoniteTab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(KryptoniteItems.kryptoniteBlockItem.get());
        }
    };

    private static final Logger _logger = LogManager.getLogger();

    public KryptoniteMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        KryptoniteItems.items.register(FMLJavaModLoadingContext.get().getModEventBus());
        KryptoniteBlocks.blocks.register(FMLJavaModLoadingContext.get().getModEventBus());
        KryptoniteEntityTypes.entityTypes.register(FMLJavaModLoadingContext.get().getModEventBus());

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        DeferredWorkQueue.runLater(() -> {
            GlobalEntityTypeAttributes.put(KryptoniteEntityTypes.gorillaEntity.get(), GorillaEntity.setCustomAttributes().create());
        });
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
    }
}
