package com.kryptonitemod.client.gui;

import com.kryptonitemod.KryptoniteMod;
import com.kryptonitemod.container.KryptoniteRefineryContainer;
import com.kryptonitemod.tileentities.KryptoniteRefineryTileEntity;
import com.kryptonitemod.util.helpers.KrypRectangle;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class KryptoniteRefineryScreen extends ContainerScreen<KryptoniteRefineryContainer> {
    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(KryptoniteMod.MOD_ID, "textures/gui/container/kryptonite_refinery.png");
    private static final KrypRectangle CHARGE_PROGRESS_RECTANGLE_BACKGROUND = new KrypRectangle(73, 27, 30, 26);
    private static final KrypRectangle CHARGE_PROGRESS_RECTANGLE_FOREGROUND = new KrypRectangle(176, 0, 30, 26);
    private static final KrypRectangle BURN_PROGRESS_RECTANGLE_BACKGROUND = new KrypRectangle(29, 36, 19, 34);
    private static final KrypRectangle BURN_PROGRESS_RECTANGLE_FOREGROUND = new KrypRectangle(176, 26, 19, 34);

    public KryptoniteRefineryScreen(final KryptoniteRefineryContainer container, final PlayerInventory inventory, final ITextComponent title) {
        super(container, inventory, title);
    }

    @Override
    public void render(MatrixStack matrixStack, final int mouseX, final int mouseY, final float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderHoveredTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, final int mouseX, final int mouseY) {
        //super.drawGuiContainerForegroundLayer(matrixStack, mouseX, mouseY); //This would render the titles of the container and the player inventory

        final KryptoniteRefineryTileEntity tileEntity = this.container.tileEntity;
        this.font.drawString(matrixStack, "Charge Time: " + tileEntity.inputChargeTimeLeft + " / " + tileEntity.maxInputChargeTime, 8.0F, this.ySize, 0xFFFFFF);
        this.font.drawString(matrixStack, "Burn Time:   " + tileEntity.fuelBurnTimeLeft + " / " + tileEntity.maxFuelBurnTime, 8.0F, this.ySize + 14, 0xFFFFFF);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, final float partialTicks, final int mouseX, final int mouseY) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        getMinecraft().getTextureManager().bindTexture(this.BACKGROUND_TEXTURE);
        int startX = this.guiLeft;
        int startY = this.guiTop;

        // Screen#blit draws a part of the current texture (assumed to be 256x256) to the screen
        // The parameters are (x, y, u, v, width, height)

        this.blit(matrixStack, startX, startY, 0, 0, this.xSize, this.ySize);

        final KryptoniteRefineryTileEntity tileEntity = container.tileEntity;
        if (tileEntity.inputChargeTimeLeft > 0) {
            // Draw progress arrow
            int progressHeight = getChargeTimeProgressHeight();
            this.blit(
                    matrixStack,
                    startX + CHARGE_PROGRESS_RECTANGLE_BACKGROUND.x,
                    startY + CHARGE_PROGRESS_RECTANGLE_BACKGROUND.y,
                    CHARGE_PROGRESS_RECTANGLE_FOREGROUND.x,
                    CHARGE_PROGRESS_RECTANGLE_FOREGROUND.y,
                    CHARGE_PROGRESS_RECTANGLE_FOREGROUND.width,
                    progressHeight
            );
        }

        if (tileEntity.isBurning()) {
            // Draw flames
            int flameHeight = getBurnTimeProgressHeight();
            this.blit(
                    matrixStack,
                    startX + BURN_PROGRESS_RECTANGLE_BACKGROUND.x,
                    startY + BURN_PROGRESS_RECTANGLE_BACKGROUND.y + (BURN_PROGRESS_RECTANGLE_FOREGROUND.height - flameHeight),
                    BURN_PROGRESS_RECTANGLE_FOREGROUND.x,
                    BURN_PROGRESS_RECTANGLE_FOREGROUND.y + (BURN_PROGRESS_RECTANGLE_FOREGROUND.height - flameHeight),
                    BURN_PROGRESS_RECTANGLE_FOREGROUND.width,
                    flameHeight
            );
        }
    }

    private int getChargeTimeProgressHeight() {
        final KryptoniteRefineryTileEntity tileEntity = this.container.tileEntity;
        final short chargeTimeLeft = tileEntity.inputChargeTimeLeft;
        final short maxChargeTime = tileEntity.maxInputChargeTime;

        if (chargeTimeLeft <= 0 || maxChargeTime <= 0)
            return 0;

        return (maxChargeTime - chargeTimeLeft) * CHARGE_PROGRESS_RECTANGLE_FOREGROUND.height / maxChargeTime;
    }

    private int getBurnTimeProgressHeight() {
        final KryptoniteRefineryTileEntity tileEntity = this.container.tileEntity;
        final short fuelBurnTimeLeft = tileEntity.fuelBurnTimeLeft;
        final short maxFuelBurnTime = tileEntity.maxFuelBurnTime;

        if (fuelBurnTimeLeft <= 0 || maxFuelBurnTime <= 0)
            return 0;

        return fuelBurnTimeLeft * BURN_PROGRESS_RECTANGLE_FOREGROUND.height / maxFuelBurnTime;
    }
}