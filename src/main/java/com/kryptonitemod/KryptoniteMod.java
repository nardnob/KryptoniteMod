package com.kryptonitemod;

import com.kryptonitemod.util.RegistryHandler;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("kryptonitemod")
public class KryptoniteMod {
    public static final String ModId = "kryptonitemod";
    private static final Logger LOGGER = LogManager.getLogger();

    public KryptoniteMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);

        RegistryHandler.init();
    }

    private void setup(final FMLCommonSetupEvent event) {
    }

    public static final ItemGroup CreativeTab = new ItemGroup("kryptoniteTab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(RegistryHandler.KRYPTONITE_BLOCK_ITEM.get());
        }
    };
}
