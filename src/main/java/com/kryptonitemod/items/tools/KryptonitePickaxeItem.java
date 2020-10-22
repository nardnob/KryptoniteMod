package com.kryptonitemod.items.tools;

import com.kryptonitemod.KryptoniteMod;
import com.kryptonitemod.util.KryptoniteItemTier;
import com.kryptonitemod.util.helpers.IKryptoniteChargeable;
import net.minecraft.item.PickaxeItem;

public class KryptonitePickaxeItem extends PickaxeItem implements IKryptoniteChargeable {
    public static final String NAME = "kryptonite_pickaxe_item";

    public KryptonitePickaxeItem() {
        super(
            KryptoniteItemTier.KRYPTONITE,
            6, //1 (all items do 1 damage) + base damage (3.0F here) + items damage (6)
            -2.4F, //4 - 2.4 = 1.6 (vanilla iron sword cooldown)
            new Properties().group(KryptoniteMod.CREATIVE_TAB)
        );
    }

    @Override
    public short getChargeTime() {
        return this.DEFAULT_CHARGE_TIME;
    }
}
