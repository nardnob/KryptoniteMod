package com.kryptonitemod.util;

import com.kryptonitemod.KryptoniteMod;
import com.kryptonitemod.blocks.KryptoniteBlock;
import com.kryptonitemod.blocks.KryptoniteOreBlock;
import com.kryptonitemod.items.*;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistryHandler {
    public static final DeferredRegister<Item> Items = DeferredRegister.create(ForgeRegistries.ITEMS, KryptoniteMod.ModId);
    public static final DeferredRegister<Block> Blocks = DeferredRegister.create(ForgeRegistries.BLOCKS, KryptoniteMod.ModId);

    //Blocks
    public static final RegistryObject<Block> KRYPTONITE_BLOCK = Blocks.register(KryptoniteBlock.Name, KryptoniteBlock::new);
    public static final RegistryObject<Block> KRYPTONITE_ORE_BLOCK = Blocks.register(KryptoniteOreBlock.Name, KryptoniteOreBlock::new);

    //Items
    public static final RegistryObject<Item> KRYPTONITE_ITEM = Items.register(KryptoniteItem.Name, KryptoniteItem::new);
    public static final RegistryObject<PickaxeItem> KRYPTONITE_PICKAXE_ITEM = Items.register(KryptonitePickaxeItem.Name, KryptonitePickaxeItem::new);
    public static final RegistryObject<ShovelItem> KRYPTONITE_SHOVEL_ITEM = Items.register(KryptoniteShovelItem.Name, KryptoniteShovelItem::new);
    public static final RegistryObject<SwordItem> KRYPTONITE_SWORD_ITEM = Items.register(KryptoniteSwordItem.Name, KryptoniteSwordItem::new);
    public static final RegistryObject<AxeItem> KRYPTONITE_AXE_ITEM = Items.register(KryptoniteAxeItem.Name, KryptoniteAxeItem::new);
    public static final RegistryObject<HoeItem> KRYPTONITE_HOE_ITEM = Items.register(KryptoniteHoeItem.Name, KryptoniteHoeItem::new);

    //BlockItems
    public static final RegistryObject<Item> KRYPTONITE_ORE_ITEM = Items.register(KryptoniteOreItem.Name, () -> new KryptoniteOreItem(KRYPTONITE_ORE_BLOCK.get()));
    public static final RegistryObject<Item> KRYPTONITE_BLOCK_ITEM = Items.register(KryptoniteBlockItem.Name, () -> new KryptoniteBlockItem(KRYPTONITE_BLOCK.get()));

    public static void init() {
        Items.register(FMLJavaModLoadingContext.get().getModEventBus());
        Blocks.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
