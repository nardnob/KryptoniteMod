package com.kryptonitemod.items.food;

import com.kryptonitemod.KryptoniteMod;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class KryptoniteLoafItem extends Item {
    public static final String name = "kryptonite_loaf_item";

    public KryptoniteLoafItem() {
        super(new Item.Properties()
                .group(KryptoniteMod.creativeTab)
                .food(new Food.Builder()
                        .hunger(6)
                        .saturation(1.3f)
                        .effect(() -> new EffectInstance(Effects.SPEED, 5 * 60 * KryptoniteMod.ticksPerSecond, 1), 1)
                        .fastToEat()
                        .meat()
                        .setAlwaysEdible()
                        .build())
        );
    }
}
