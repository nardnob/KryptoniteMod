package com.kryptonitemod.util;

import com.kryptonitemod.KryptoniteMod;
import com.kryptonitemod.blocks.KryptoniteBlock;
import com.kryptonitemod.blocks.KryptoniteOreBlock;
import com.kryptonitemod.items.KryptoniteBlockItem;
import com.kryptonitemod.items.KryptoniteItem;
import com.kryptonitemod.items.KryptoniteOreItem;
import com.kryptonitemod.tools.KryptoniteItemTier;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
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

    //BlockItems
    public static final RegistryObject<Item> KRYPTONITE_ORE_ITEM = Items.register(KryptoniteOreItem.Name, () -> new KryptoniteOreItem(KRYPTONITE_ORE_BLOCK.get()));
    public static final RegistryObject<Item> KRYPTONITE_BLOCK_ITEM = Items.register(KryptoniteBlockItem.Name, () -> new KryptoniteBlockItem(KRYPTONITE_BLOCK.get()));

    //Tools
    public static final RegistryObject<SwordItem> KRYPTONITE_SWORD_ITEM = Items.register("kryptonite_sword_item", () ->
            new SwordItem(
                    KryptoniteItemTier.KRYPTONITE,
                    6, //1 (all items do 1 damage) + base damage (3.0F here) + items damage (6)
                    -2.4F, //4 - 2.4 = 1.6 (vanilla iron sword cooldown)
                    new Item.Properties().group(KryptoniteMod.CreativeTab)
            ));

    public static void init() {
        Items.register(FMLJavaModLoadingContext.get().getModEventBus());
        Blocks.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
