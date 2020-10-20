package com.kryptonitemod.events;

import com.kryptonitemod.KryptoniteMod;
import com.kryptonitemod.init.KryptoniteItems;
import com.kryptonitemod.tileentities.KryptoniteRefineryTileEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = KryptoniteMod.modId, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class KryptoniteClientEvents {
    /*
    @SubscribeEvent
    public static void onKryptoniteSwordAttack(AttackEntityEvent event) {
        PlayerEntity player = event.getPlayer();

        if (player.getEntityWorld().isRemote) {
            return;
        }

        if (event.getEntityLiving().getHeldItemMainhand().getItem() == KryptoniteItems.kryptoniteSwordItem.get()) {
            if (event.getTarget().isAlive()) {
                LivingEntity target = (LivingEntity) event.getTarget();

                target.setGlowing(true);
                String message = TextFormatting.GREEN + "The creature glows with radiation.";
                player.sendMessage(new StringTextComponent(message), player.getUniqueID());
            }
        }
    }
    */
}
