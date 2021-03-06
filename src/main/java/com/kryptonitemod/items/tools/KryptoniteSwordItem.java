package com.kryptonitemod.items.tools;

import com.kryptonitemod.KryptoniteMod;
import com.kryptonitemod.util.KryptoniteItemTier;
import com.kryptonitemod.util.helpers.IKryptoniteChargeable;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;

public class KryptoniteSwordItem extends SwordItem implements IKryptoniteChargeable {
    public static final String NAME = "kryptonite_sword_item";

    public KryptoniteSwordItem() {
        super(
            KryptoniteItemTier.KRYPTONITE,
            6, //1 (all items do 1 damage) + base damage (3.0F here) + items damage (6)
            -2.4F, //4 - 2.4 = 1.6 (vanilla iron sword cooldown)
            new Item.Properties().group(KryptoniteMod.CREATIVE_TAB)
        );
    }

    @Override
    public short getChargeTime() {
        return this.DEFAULT_CHARGE_TIME;
    }
}
