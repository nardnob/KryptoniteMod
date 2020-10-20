package com.kryptonitemod.util;

import com.kryptonitemod.KryptoniteMod;
import com.kryptonitemod.client.gui.KryptoniteRefineryScreen;
import com.kryptonitemod.client.render.GorillaEntityRenderer;
import com.kryptonitemod.entities.GorillaEntity;
import com.kryptonitemod.init.KryptoniteContainerTypes;
import com.kryptonitemod.init.KryptoniteEntityTypes;
import com.kryptonitemod.items.spawneggs.KryptoniteSpawnEggItem;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = KryptoniteMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        KrypLogger.LOGGER.debug("Registered TileEntity Renderers");

        RenderingRegistry.registerEntityRenderingHandler(KryptoniteEntityTypes.GORILLA.get(), GorillaEntityRenderer::new);
        KrypLogger.LOGGER.debug("Registered Entity Renderers");

        //TODO kryp - replace deprecated function after migration back to 1.16.3
        //event.enqueueWork
        DeferredWorkQueue.runLater(() -> {
            ScreenManager.registerFactory(KryptoniteContainerTypes.KRYPTONITE_REFINERY.get(), KryptoniteRefineryScreen::new);
            KrypLogger.LOGGER.debug("Registered ContainerType Screens");

            GlobalEntityTypeAttributes.put(KryptoniteEntityTypes.GORILLA.get(), GorillaEntity.setCustomAttributes().create());
            KrypLogger.LOGGER.debug("Registered GlobalTypeAttributes");
        });
    }

    @SubscribeEvent
    public static void onRegisterEntities(final RegistryEvent.Register<EntityType<?>> event) {
        KryptoniteSpawnEggItem.initSpawnEggs();
    }
}
