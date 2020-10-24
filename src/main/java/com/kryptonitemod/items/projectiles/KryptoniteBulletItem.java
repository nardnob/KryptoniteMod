package com.kryptonitemod.items.projectiles;

import com.kryptonitemod.KryptoniteMod;
import com.kryptonitemod.entities.projectiles.KryptoniteBulletEntity;
import com.kryptonitemod.init.KryptoniteEntityTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class KryptoniteBulletItem extends Item {
    public static final String NAME = "kryptonite_bullet_item";

    public KryptoniteBulletItem() {
        super(new Properties().group(KryptoniteMod.CREATIVE_TAB));
    }

    /**
     * Called to trigger the item's "innate" right click behavior. To handle when this item is used on a Block, see
     * {@link #onItemUse}.
     */
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);

        if (!worldIn.isRemote) {
            KryptoniteBulletEntity bulletEntity = new KryptoniteBulletEntity(KryptoniteEntityTypes.KRYPTONITE_BULLET.get(), worldIn);
            bulletEntity.setItem(itemstack);
            bulletEntity.setPosition(playerIn.getPosX(), playerIn.getPosYEye() - (double)0.1F, playerIn.getPosZ());
            bulletEntity.setShooter(playerIn);
            bulletEntity.func_234612_a_(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
            worldIn.addEntity(bulletEntity);
        }

        return ActionResult.func_233538_a_(itemstack, worldIn.isRemote());
    }
}
