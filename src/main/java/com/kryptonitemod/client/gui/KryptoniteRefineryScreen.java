package com.kryptonitemod.client.gui;

import com.kryptonitemod.KryptoniteMod;
import com.kryptonitemod.container.KryptoniteRefineryContainer;
import com.kryptonitemod.tileentities.KryptoniteRefineryTileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class KryptoniteRefineryScreen extends ContainerScreen<KryptoniteRefineryContainer> {
    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation("minecraft", "textures/gui/container/furnace.png");

    public KryptoniteRefineryScreen(final KryptoniteRefineryContainer container, final PlayerInventory inventory, final ITextComponent title) {
        super(container, inventory, title);
    }

    @Override
    public void render(MatrixStack matrixStack, final int mouseX, final int mouseY, final float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderHoveredTooltip(matrixStack, mouseX, mouseY);

        int relMouseX = mouseX - this.guiLeft;
        int relMouseY = mouseY - this.guiTop;
        final KryptoniteRefineryTileEntity tileEntity = this.container.tileEntity;
        boolean arrowHovered = relMouseX > 79 && relMouseX < 104 && relMouseY > 34 && relMouseY < 50;
        if (arrowHovered && tileEntity.maxSmeltTime > 0) {
            TranslationTextComponent tooltip = new TranslationTextComponent(
                    "gui." + KryptoniteMod.MOD_ID + ".smeltTimeProgress",
                    tileEntity.smeltTimeLeft, tileEntity.maxSmeltTime
            );
            this.renderTooltip(matrixStack, tooltip, mouseX, mouseY);
        }
        boolean fireHovered = relMouseX > 56 && relMouseX < 70 && relMouseY > 36 && relMouseY < 50;
        if (fireHovered && tileEntity.maxFuelBurnTime > 0) {
            TranslationTextComponent tooltip = new TranslationTextComponent(
                    "gui." + KryptoniteMod.MOD_ID + ".fuelBurnTimeProgress",
                    tileEntity.fuelBurnTimeLeft, tileEntity.maxFuelBurnTime
            );
            this.renderTooltip(matrixStack, tooltip, mouseX, mouseY);
        }
    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, final int mouseX, final int mouseY) {
        super.drawGuiContainerForegroundLayer(matrixStack, mouseX, mouseY);

        final KryptoniteRefineryTileEntity tileEntity = this.container.tileEntity;
        if (tileEntity.smeltTimeLeft > 0)
            this.font.drawString(matrixStack, tileEntity.smeltTimeLeft + " / " + tileEntity.maxSmeltTime, 8.0F, this.ySize, 0xFFFFFF);
        this.font.drawString(matrixStack, tileEntity.fuelBurnTimeLeft + " / " + tileEntity.maxFuelBurnTime, 8.0F, this.ySize + 14, 0xFFFFFF);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, final float partialTicks, final int mouseX, final int mouseY) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        getMinecraft().getTextureManager().bindTexture(BACKGROUND_TEXTURE);
        int startX = this.guiLeft;
        int startY = this.guiTop;

        // Screen#blit draws a part of the current texture (assumed to be 256x256) to the screen
        // The parameters are (x, y, u, v, width, height)

        this.blit(matrixStack, startX, startY, 0, 0, this.xSize, this.ySize);

        final KryptoniteRefineryTileEntity tileEntity = container.tileEntity;
        if (tileEntity.smeltTimeLeft > 0) {
            // Draw progress arrow
            int arrowWidth = getSmeltTimeScaled();
            this.blit(
                    matrixStack,
                    startX + 79, startY + 34,
                    176, 14,
                    arrowWidth, 17
            );
        }
        if (tileEntity.isBurning()) {
            // Draw flames
            int flameHeight = getFuelBurnTimeScaled();
            this.blit(
                    matrixStack,
                    startX + 56, startY + 50 - flameHeight,
                    176, 14 - flameHeight,
                    14, flameHeight
            );
        }
    }

    private int getSmeltTimeScaled() {
        final KryptoniteRefineryTileEntity tileEntity = this.container.tileEntity;
        final short smeltTimeLeft = tileEntity.smeltTimeLeft;
        final short maxSmeltTime = tileEntity.maxSmeltTime;
        if (smeltTimeLeft <= 0 || maxSmeltTime <= 0)
            return 0;
        return (maxSmeltTime - smeltTimeLeft) * 24 / maxSmeltTime; // 24 is the width of the arrow
    }

    private int getFuelBurnTimeScaled() {
        final KryptoniteRefineryTileEntity tileEntity = this.container.tileEntity;
        if (tileEntity.maxFuelBurnTime <= 0)
            return 0;
        return tileEntity.fuelBurnTimeLeft * 16 / tileEntity.maxFuelBurnTime; // 14 is the height of the flames
    }
}