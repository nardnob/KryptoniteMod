package com.kryptonitemod.items.spawneggs;

import com.kryptonitemod.KryptoniteMod;
import com.kryptonitemod.init.KryptoniteEntityTypes;
import net.minecraft.item.Item;

public class GorillaSpawnEggItem extends KryptoniteSpawnEggItem {
    public static final String NAME = "gorilla_spawn_egg_item";

    public GorillaSpawnEggItem() {
        super(KryptoniteEntityTypes.GORILLA, 0x222222, 0x555555, new Item.Properties().group(KryptoniteMod.CREATIVE_TAB));
    }
}
