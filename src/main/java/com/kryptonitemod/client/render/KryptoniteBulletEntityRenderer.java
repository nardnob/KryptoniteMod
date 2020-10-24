package com.kryptonitemod.client.render;

import com.kryptonitemod.KryptoniteMod;
import com.kryptonitemod.entities.projectiles.KryptoniteBulletEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.util.ResourceLocation;

public class KryptoniteBulletEntityRenderer extends SpriteRenderer<KryptoniteBulletEntity> {
    protected static final ResourceLocation TEXTURE = new ResourceLocation(KryptoniteMod.MOD_ID, "textures/items/kryptonite_bullet_item.png");

    public KryptoniteBulletEntityRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, Minecraft.getInstance().getItemRenderer());
    }
}