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
    public static final DeferredRegister<Block> blocks = DeferredRegister.create(ForgeRegistries.BLOCKS, KryptoniteMod.modId);

    public static final RegistryObject<Block> kryptoniteBlock = blocks.register(KryptoniteBlock.name, KryptoniteBlock::new);
    public static final RegistryObject<Block> kryptoniteOreBlock = blocks.register(KryptoniteOreBlock.name, KryptoniteOreBlock::new);
    public static final RegistryObject<Block> kryptoniteRefineryBlock = blocks.register(KryptoniteRefineryBlock.name, KryptoniteRefineryBlock::new);
}
