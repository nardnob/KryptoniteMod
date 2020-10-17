package com.kryptonitemod;

import com.kryptonitemod.entities.GorillaEntity;
import com.kryptonitemod.init.KryptoniteEntityTypes;
import com.kryptonitemod.util.RegistryHandler;
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
    public static final String ModId = "kryptonitemod";
    public static final int TicksPerSecond = 20;

    private static final Logger LOGGER = LogManager.getLogger();

    public KryptoniteMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        RegistryHandler.init();
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

    public static final ItemGroup CreativeTab = new ItemGroup("kryptoniteTab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(RegistryHandler.KRYPTONITE_BLOCK_ITEM.get());
        }
    };
}
