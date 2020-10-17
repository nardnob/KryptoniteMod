package com.kryptonitemod.world.gen;

import com.kryptonitemod.KryptoniteMod;
import com.kryptonitemod.init.KryptoniteEntityTypes;
import net.minecraft.entity.EntityClassification;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = KryptoniteMod.modId, bus = Mod.EventBusSubscriber.Bus.MOD)
public class KryptoniteEntitySpawns {
    @SubscribeEvent
    public static void spawnEntities(FMLLoadCompleteEvent event) {
        for (Biome biome : ForgeRegistries.BIOMES) {
            boolean isLand = biome.getCategory() != Biome.Category.OCEAN;
            boolean isNether = biome.getCategory() == Biome.Category.NETHER;
            boolean isTheEnd = biome.getCategory() == Biome.Category.THEEND;

            if (isNether) {
            } else if (isTheEnd) {

            } else {
                if (isLand) {
                    biome.getSpawns(EntityClassification.CREATURE)
                            .add(new Biome.SpawnListEntry(KryptoniteEntityTypes.gorillaEntity.get(), 10, 3, 5));
                }
            }
        }
    }
}
