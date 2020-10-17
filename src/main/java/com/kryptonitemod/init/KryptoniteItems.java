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
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class KryptoniteItems {
    public static final DeferredRegister<Item> items = DeferredRegister.create(ForgeRegistries.ITEMS, KryptoniteMod.modId);

    //Misc
    public static final RegistryObject<Item> kryptoniteItem = items.register(KryptoniteItem.name, KryptoniteItem::new);

    //Spawn Eggs
    public static final RegistryObject<Item> gorillaSpawnEggItem = items.register(GorillaSpawnEggItem.name, GorillaSpawnEggItem::new);

    //Food
    public static final RegistryObject<Item> kryptoniteLoafItem = items.register(KryptoniteLoafItem.name, KryptoniteLoafItem::new);

    //Tools
    public static final RegistryObject<Item> kryptonitePickaxeItem = items.register(KryptonitePickaxeItem.name, KryptonitePickaxeItem::new);
    public static final RegistryObject<Item> kryptoniteShovelItem = items.register(KryptoniteShovelItem.name, KryptoniteShovelItem::new);
    public static final RegistryObject<Item> kryptoniteSwordItem = items.register(KryptoniteSwordItem.name, KryptoniteSwordItem::new);
    public static final RegistryObject<Item> kryptoniteAxeItem = items.register(KryptoniteAxeItem.name, KryptoniteAxeItem::new);
    public static final RegistryObject<Item> kryptoniteHoeItem = items.register(KryptoniteHoeItem.name, KryptoniteHoeItem::new);

    //Armor
    public static final RegistryObject<Item> kryptoniteHelmetItem = items.register(KryptoniteHelmetItem.name, KryptoniteHelmetItem::new);
    public static final RegistryObject<Item> kryptoniteChestItem = items.register(KryptoniteChestplateItem.name, KryptoniteChestplateItem::new);
    public static final RegistryObject<Item> kryptoniteLeggingsItem = items.register(KryptoniteLeggingsItem.name, KryptoniteLeggingsItem::new);
    public static final RegistryObject<Item> kryptoniteBootsItem = items.register(KryptoniteBootsItem.name, KryptoniteBootsItem::new);


    //BlockItems
    public static final RegistryObject<Item> kryptoniteOreItem = items.register(KryptoniteOreItem.name, () -> new KryptoniteOreItem(KryptoniteBlocks.kryptoniteOreBlock.get()));
    public static final RegistryObject<Item> kryptoniteBlockItem = items.register(KryptoniteBlockItem.name, () -> new KryptoniteBlockItem(KryptoniteBlocks.kryptoniteBlock.get()));
}
