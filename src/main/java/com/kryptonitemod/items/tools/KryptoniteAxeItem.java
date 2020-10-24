package com.kryptonitemod.items.tools;

import com.kryptonitemod.KryptoniteMod;
import com.kryptonitemod.util.KryptoniteItemTier;
import com.kryptonitemod.util.helpers.IKryptoniteChargeable;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.PotionEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class KryptoniteAxeItem extends AxeItem implements IKryptoniteChargeable {
    public static final String NAME = "kryptonite_axe_item";

    public KryptoniteAxeItem() {
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

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemStack = playerIn.getHeldItem(handIn);

        if (!worldIn.isRemote) {
            //shootLikePotion(worldIn, playerIn, handIn, itemStack);
            shootLikeArrow(worldIn, playerIn, handIn, itemStack);
        }
        /*

        playerIn.addStat(Stats.ITEM_USED.get(this));
        if (!playerIn.abilities.isCreativeMode) {
            itemstack.shrink(1);
        }
        */
        return ActionResult.func_233538_a_(itemStack, worldIn.isRemote());
    }

    private void shootLikePotion(World worldIn, PlayerEntity playerIn, Hand handIn, ItemStack itemStack) {
        PotionEntity potionentity = new PotionEntity(worldIn, playerIn);
        potionentity.setItem(itemStack);
        potionentity.func_234612_a_(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, -20.0F, 0.5F, 1.0F);
        worldIn.addEntity(potionentity);
    }

    private void shootLikeArrow(World worldIn, PlayerEntity playerIn, Hand handIn, ItemStack itemStack) {
        boolean flag = playerIn.abilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, itemStack) > 0;
        ItemStack itemstack = playerIn.findAmmo(itemStack);

        float f = 1.0F;//getArrowVelocity(i);
        if (!((double)f < 0.1D)) {
            if (!worldIn.isRemote) {
                ArrowItem arrowitem = (ArrowItem)(itemstack.getItem() instanceof ArrowItem ? itemstack.getItem() : Items.ARROW);
                AbstractArrowEntity abstractarrowentity = arrowitem.createArrow(worldIn, itemstack, playerIn);
                abstractarrowentity.func_234612_a_(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, f * 3.0F, 1.0F);
                /*
                if (f == 1.0F) {
                    abstractarrowentity.setIsCritical(true);
                }

                int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, itemstack);
                if (j > 0) {
                    abstractarrowentity.setDamage(abstractarrowentity.getDamage() + (double)j * 0.5D + 0.5D);
                }

                int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, itemStack);
                if (k > 0) {
                    abstractarrowentity.setKnockbackStrength(k);
                }

                if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, itemStack) > 0) {
                    abstractarrowentity.setFire(100);
                }

                itemStack.damageItem(1, playerIn, (p_220009_1_) -> {
                    p_220009_1_.sendBreakAnimation(playerIn.getActiveHand());
                });
                 */

                worldIn.addEntity(abstractarrowentity);
            }

            worldIn.playSound((PlayerEntity)null, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
        }
    }
}
