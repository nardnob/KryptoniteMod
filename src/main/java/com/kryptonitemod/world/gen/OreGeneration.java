package com.kryptonitemod.world.gen;

import com.kryptonitemod.KryptoniteMod;
import com.kryptonitemod.init.KryptoniteBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.pattern.BlockMatcher;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = KryptoniteMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OreGeneration {
    public static OreFeatureConfig.FillerBlockType endStone = OreFeatureConfig.FillerBlockType.create(
            "END_STONE", "end_stone", new BlockMatcher(Blocks.END_STONE));

    @SubscribeEvent
    public static void generateOres(FMLLoadCompleteEvent event) {
        for (Biome biome : ForgeRegistries.BIOMES) {
            //Nether generation
            if (biome.getCategory() == Biome.Category.NETHER) {
                registerOre(biome, 60, 45, 0, 80, OreFeatureConfig.FillerBlockType.NETHERRACK,
                        KryptoniteBlocks.KRYPTONITE_ORE.get().getDefaultState(), 10);

            //End Generation
            } else if (biome.getCategory() == Biome.Category.THEEND) {
                registerOre(biome, 60, 45, 0, 80, endStone,
                        KryptoniteBlocks.KRYPTONITE_ORE.get().getDefaultState(), 10);

            //World Generation
            } else {
                registerOre(biome, 60, 45, 0, 80, OreFeatureConfig.FillerBlockType.NATURAL_STONE,
                        KryptoniteBlocks.KRYPTONITE_ORE.get().getDefaultState(), 10);
            }
        }
    }

    private static void registerOre(Biome biome, int count, int bottomOffset, int topOffset, int max,
                                    OreFeatureConfig.FillerBlockType filler, BlockState defaultBlockState, int size) {
        //maxY: max - topOffset
        //minY: bottomOffset

        CountRangeConfig range = new CountRangeConfig(count, bottomOffset, topOffset, max);
        OreFeatureConfig feature = new OreFeatureConfig(filler, defaultBlockState, size);
        ConfiguredPlacement config = Placement.COUNT_RANGE.configure(range);
        biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(feature).withPlacement(config));
    }
}
