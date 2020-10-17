package com.kryptonitemod.items.spawneggs;

import com.kryptonitemod.KryptoniteMod;
import com.kryptonitemod.init.KryptoniteEntityTypes;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

public class GorillaSpawnEggItem extends KryptoniteSpawnEggItem {
    public static final String name = "gorilla_spawn_egg_item";

    public GorillaSpawnEggItem() {
        super(KryptoniteEntityTypes.gorillaEntity, 0x222222, 0x555555, new Item.Properties().group(KryptoniteMod.creativeTab));
    }
}
