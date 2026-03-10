package com.belyga.decor.gui;

import com.belyga.decor.inventory.ContainerDecorCrate;
import com.belyga.decor.tileentity.TileEntityDecorCrate;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiDecorCrate extends GuiContainer {
    private static final ResourceLocation BG = new ResourceLocation("textures/gui/container/generic_54.png");

    public GuiDecorCrate(InventoryPlayer playerInv, TileEntityDecorCrate te) {
        super(new ContainerDecorCrate(playerInv, te));
        int rows = (int) Math.ceil(te.getSizeInventory() / (double) Math.min(9, te.getSizeInventory()));
        this.ySize = 114 + rows * 18;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        mc.getTextureManager().bindTexture(BG);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }
}
