package com.kryptonitemod.init;

import com.kryptonitemod.KryptoniteMod;
import com.kryptonitemod.items.KryptoniteBlockItem;
import com.kryptonitemod.items.KryptoniteItem;
import com.kryptonitemod.items.KryptoniteOreItem;
import com.kryptonitemod.items.armor.KryptoniteBootsItem;
import com.kryptonitemod.items.armor.KryptoniteChestplateItem;
import com.kryptonitemod.items.armor.KryptoniteHelmetItem;
import com.kryptonitemod.items.armor.KryptoniteLeggingsItem;
import com.kryptonitemod.items.food.KryptoniteLoafItem;
import com.kryptonitemod.items.tools.*;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class KryptoniteItems {
    public static final DeferredRegister<Item> items = DeferredRegister.create(ForgeRegistries.ITEMS, KryptoniteMod.modId);

    public static final RegistryObject<Item> kryptoniteItem = items.register(KryptoniteItem.name, KryptoniteItem::new);
    //Food
    public static final RegistryObject<KryptoniteLoafItem> kryptoniteLoafItem = items.register(KryptoniteLoafItem.name, KryptoniteLoafItem::new);
    //Tools
    public static final RegistryObject<PickaxeItem> kryptonitePickaxeItem = items.register(KryptonitePickaxeItem.name, KryptonitePickaxeItem::new);
    public static final RegistryObject<ShovelItem> kryptoniteShovelItem = items.register(KryptoniteShovelItem.name, KryptoniteShovelItem::new);
    public static final RegistryObject<SwordItem> kryptoniteSwordItem = items.register(KryptoniteSwordItem.name, KryptoniteSwordItem::new);
    public static final RegistryObject<AxeItem> kryptoniteAxeItem = items.register(KryptoniteAxeItem.name, KryptoniteAxeItem::new);
    public static final RegistryObject<HoeItem> kryptoniteHoeItem = items.register(KryptoniteHoeItem.name, KryptoniteHoeItem::new);
    //Armor
    public static final RegistryObject<ArmorItem> kryptoniteHelmetItem = items.register(KryptoniteHelmetItem.name, KryptoniteHelmetItem::new);
    public static final RegistryObject<ArmorItem> kryptoniteChestItem = items.register(KryptoniteChestplateItem.name, KryptoniteChestplateItem::new);
    public static final RegistryObject<ArmorItem> kryptoniteLeggingsItem = items.register(KryptoniteLeggingsItem.name, KryptoniteLeggingsItem::new);
    public static final RegistryObject<ArmorItem> kryptoniteBootsItem = items.register(KryptoniteBootsItem.name, KryptoniteBootsItem::new);

    //BlockItems
    public static final RegistryObject<Item> kryptoniteOreItem = items.register(KryptoniteOreItem.name, () -> new KryptoniteOreItem(KryptoniteBlocks.kryptoniteOreBlock.get()));
    public static final RegistryObject<Item> kryptoniteBlockItem = items.register(KryptoniteBlockItem.name, () -> new KryptoniteBlockItem(KryptoniteBlocks.kryptoniteBlock.get()));
}
