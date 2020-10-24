package com.kryptonitemod.client.model;

import com.kryptonitemod.entities.passive.GorillaEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class GorillaEntityModel<T extends GorillaEntity> extends EntityModel<T> {
    private final ModelRenderer butt_left;
    private final ModelRenderer butt_right;
    private final ModelRenderer neck;
    private final ModelRenderer body;
    private final ModelRenderer head;
    private final ModelRenderer right_arm;
    private final ModelRenderer left_arm;
    private final ModelRenderer left_leg;
    private final ModelRenderer right_leg;

    public GorillaEntityModel() {
        textureWidth = 64;
        textureHeight = 64;

        butt_left = new ModelRenderer(this);
        butt_left.setRotationPoint(-0.5F, 12.4485F, 7.7493F);
        setRotationAngle(butt_left, 0.9163F, 0.0F, 0.0F);
        butt_left.setTextureOffset(12, 35).addBox(-3.5F, -0.7608F, -0.4018F, 3.0F, 1.0F, 1.0F, 0.0F, false);
        butt_left.setTextureOffset(43, 34).addBox(-4.5F, 0.2392F, -0.4018F, 5.0F, 2.0F, 1.0F, 0.0F, false);
        butt_left.setTextureOffset(27, 2).addBox(-3.5F, 2.2392F, -0.4018F, 3.0F, 1.0F, 1.0F, 0.0F, false);

        butt_right = new ModelRenderer(this);
        butt_right.setRotationPoint(-0.5F, 13.4485F, 8.7493F);
        setRotationAngle(butt_right, 0.9163F, 0.0F, 0.0F);
        butt_right.setTextureOffset(12, 33).addBox(1.5F, -2.1629F, -0.2172F, 3.0F, 1.0F, 1.0F, 0.0F, false);
        butt_right.setTextureOffset(28, 40).addBox(0.5F, -1.1629F, -0.2172F, 5.0F, 2.0F, 1.0F, 0.0F, false);
        butt_right.setTextureOffset(27, 0).addBox(1.5F, 0.8371F, -0.2172F, 3.0F, 1.0F, 1.0F, 0.0F, false);

        neck = new ModelRenderer(this);
        neck.setRotationPoint(0.0F, 9.5F, -1.0F);
        setRotationAngle(neck, 0.6981F, 0.0F, 0.0F);
        neck.setTextureOffset(43, 27).addBox(-2.0F, -3.5F, -2.5F, 4.0F, 4.0F, 3.0F, 0.0F, false);

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 15.25F, 2.0F);
        setRotationAngle(body, 0.9163F, 0.0F, 0.0F);
        body.setTextureOffset(0, 0).addBox(-5.0F, -6.9644F, -1.4679F, 10.0F, 13.0F, 7.0F, 0.0F, false);

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, 7.5F, -2.5F);
        head.setTextureOffset(0, 20).addBox(-4.0F, -5.5F, -3.0F, 8.0F, 7.0F, 6.0F, 0.0F, false);
        head.setTextureOffset(0, 47).addBox(-2.0F, -1.5F, -4.0F, 4.0F, 3.0F, 1.0F, 0.0F, false);
        head.setTextureOffset(0, 51).addBox(-3.0F, -4.5F, -4.0F, 6.0F, 1.0F, 1.0F, 0.0F, false);

        right_arm = new ModelRenderer(this);
        right_arm.setRotationPoint(-8.0F, 14.5F, -1.0F);
        right_arm.setTextureOffset(34, 0).addBox(-1.0F, -7.5F, -1.0F, 4.0F, 10.0F, 4.0F, 0.0F, false);
        right_arm.setTextureOffset(23, 28).addBox(-2.0F, 2.5F, -2.0F, 5.0F, 7.0F, 5.0F, 0.0F, false);

        left_arm = new ModelRenderer(this);
        left_arm.setRotationPoint(8.0F, 14.5F, -1.0F);
        left_arm.setTextureOffset(0, 33).addBox(-3.0F, -7.5F, -1.0F, 4.0F, 10.0F, 4.0F, 0.0F, false);
        left_arm.setTextureOffset(29, 15).addBox(-3.0F, 2.5F, -2.0F, 5.0F, 7.0F, 5.0F, 0.0F, false);

        left_leg = new ModelRenderer(this);
        left_leg.setRotationPoint(4.0F, 18.5F, 4.0F);
        left_leg.setTextureOffset(16, 40).addBox(-3.0F, -1.5F, 0.0F, 4.0F, 7.0F, 4.0F, 0.0F, false);

        right_leg = new ModelRenderer(this);
        right_leg.setRotationPoint(-4.0F, 20.5F, 4.0F);
        right_leg.setTextureOffset(39, 39).addBox(-1.0F, -3.5F, 0.0F, 4.0F, 7.0F, 4.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
        this.head.rotateAngleX = headPitch * ((float)Math.PI / 180F);
        this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
        //this.body.rotateAngleX = ((float)Math.PI / 2F);
        this.right_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.left_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.right_arm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.left_arm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        butt_left.render(matrixStack, buffer, packedLight, packedOverlay);
        butt_right.render(matrixStack, buffer, packedLight, packedOverlay);
        neck.render(matrixStack, buffer, packedLight, packedOverlay);
        body.render(matrixStack, buffer, packedLight, packedOverlay);
        head.render(matrixStack, buffer, packedLight, packedOverlay);
        right_arm.render(matrixStack, buffer, packedLight, packedOverlay);
        left_arm.render(matrixStack, buffer, packedLight, packedOverlay);
        left_leg.render(matrixStack, buffer, packedLight, packedOverlay);
        right_leg.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}