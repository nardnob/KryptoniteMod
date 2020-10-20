package com.kryptonitemod.init;

import com.kryptonitemod.KryptoniteMod;
import com.kryptonitemod.items.ore.KryptoniteBlockItem;
import com.kryptonitemod.items.ore.KryptoniteItem;
import com.kryptonitemod.items.ore.KryptoniteOreItem;
import com.kryptonitemod.items.armor.KryptoniteBootsItem;
import com.kryptonitemod.items.armor.KryptoniteChestplateItem;
import com.kryptonitemod.items.armor.KryptoniteHelmetItem;
import com.kryptonitemod.items.armor.KryptoniteLeggingsItem;
import com.kryptonitemod.items.food.KryptoniteLoafItem;
import com.kryptonitemod.items.spawneggs.GorillaSpawnEggItem;
import com.kryptonitemod.items.tools.*;
import com.kryptonitemod.items.utility.KryptoniteRefineryItem;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class KryptoniteItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, KryptoniteMod.MOD_ID);

    //Misc
    public static final RegistryObject<Item> KRYPTONITE = ITEMS.register(KryptoniteItem.NAME, KryptoniteItem::new);

    //Spawn Eggs
    public static final RegistryObject<Item> GORILLA_SPAWN_EGG = ITEMS.register(GorillaSpawnEggItem.NAME, GorillaSpawnEggItem::new);

    //Food
    public static final RegistryObject<Item> KRYPTONITE_LOAF = ITEMS.register(KryptoniteLoafItem.NAME, KryptoniteLoafItem::new);

    //Tools
    public static final RegistryObject<Item> KRYPTONITE_PICKAXE = ITEMS.register(KryptonitePickaxeItem.NAME, KryptonitePickaxeItem::new);
    public static final RegistryObject<Item> KRYPTONITE_SHOVEL = ITEMS.register(KryptoniteShovelItem.NAME, KryptoniteShovelItem::new);
    public static final RegistryObject<Item> KRYPTONITE_SWORD = ITEMS.register(KryptoniteSwordItem.NAME, KryptoniteSwordItem::new);
    public static final RegistryObject<Item> KRYPTONITE_AXE = ITEMS.register(KryptoniteAxeItem.NAME, KryptoniteAxeItem::new);
    public static final RegistryObject<Item> KRYPTONITE_HOE = ITEMS.register(KryptoniteHoeItem.NAME, KryptoniteHoeItem::new);

    //Armor
    public static final RegistryObject<Item> KRYPTONITE_HELMET = ITEMS.register(KryptoniteHelmetItem.NAME, KryptoniteHelmetItem::new);
    public static final RegistryObject<Item> KRYPTONITE_CHEST = ITEMS.register(KryptoniteChestplateItem.NAME, KryptoniteChestplateItem::new);
    public static final RegistryObject<Item> KRYPTONITE_LEGGINGS = ITEMS.register(KryptoniteLeggingsItem.NAME, KryptoniteLeggingsItem::new);
    public static final RegistryObject<Item> KRYPTONITE_BOOTS = ITEMS.register(KryptoniteBootsItem.NAME, KryptoniteBootsItem::new);

    //BlockItems
    public static final RegistryObject<Item> KRYPTONITE_ORE = ITEMS.register(KryptoniteOreItem.NAME, () -> new KryptoniteOreItem(KryptoniteBlocks.KRYPTONITE_ORE.get()));
    public static final RegistryObject<Item> KRYPTONITE_BLOCK = ITEMS.register(KryptoniteBlockItem.NAME, () -> new KryptoniteBlockItem(KryptoniteBlocks.KRYPTONITE.get()));
    public static final RegistryObject<Item> KRYPTONITE_REFINERY = ITEMS.register(KryptoniteRefineryItem.NAME, () -> new KryptoniteRefineryItem(KryptoniteBlocks.KRYPTONITE_REFINERY.get()));
}
