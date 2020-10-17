package com.kryptonitemod.client.render;

import com.kryptonitemod.KryptoniteMod;
import com.kryptonitemod.client.model.GorillaEntityModel;
import com.kryptonitemod.entities.GorillaEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class GorillaEntityRenderer extends MobRenderer<GorillaEntity, GorillaEntityModel<GorillaEntity>> {
    protected static final ResourceLocation texture = new ResourceLocation(KryptoniteMod.ModId, "textures/entity/gorilla_entity.png");

    public GorillaEntityRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new GorillaEntityModel<>(), 0.7F);
    }

    @Override
    public ResourceLocation getEntityTexture(GorillaEntity entity) {
        return this.texture;
    }
}