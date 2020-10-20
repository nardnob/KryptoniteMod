package com.kryptonitemod.init;

import com.kryptonitemod.KryptoniteMod;
import com.kryptonitemod.blocks.KryptoniteBlock;
import com.kryptonitemod.blocks.KryptoniteOreBlock;
import com.kryptonitemod.blocks.KryptoniteRefineryBlock;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class KryptoniteBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, KryptoniteMod.MOD_ID);

    public static final RegistryObject<Block> KRYPTONITE = BLOCKS.register(KryptoniteBlock.NAME, KryptoniteBlock::new);
    public static final RegistryObject<Block> KRYPTONITE_ORE = BLOCKS.register(KryptoniteOreBlock.NAME, KryptoniteOreBlock::new);
    public static final RegistryObject<Block> KRYPTONITE_REFINERY = BLOCKS.register(KryptoniteRefineryBlock.NAME, KryptoniteRefineryBlock::new);
}
