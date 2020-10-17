package com.kryptonitemod.util;

import com.kryptonitemod.KryptoniteMod;
import com.kryptonitemod.client.render.GorillaEntityRenderer;
import com.kryptonitemod.init.KryptoniteEntityTypes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = KryptoniteMod.ModId, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(KryptoniteEntityTypes.gorillaEntity.get(), GorillaEntityRenderer::new);
    }
}
