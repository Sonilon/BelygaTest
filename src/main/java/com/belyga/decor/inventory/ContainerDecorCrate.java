package com.belyga.decor.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.tileentity.TileEntity;
import com.belyga.decor.tileentity.TileEntityDecorCrate;

public class ContainerDecorCrate extends Container {
    private final TileEntityDecorCrate crate;

    public ContainerDecorCrate(InventoryPlayer playerInventory, TileEntityDecorCrate crate) {
        this.crate = crate;
        int slots = crate.getSizeInventory();
        int cols = Math.min(9, Math.max(1, slots));
        int rows = (int) Math.ceil(slots / (float) cols);

        for (int i = 0; i < slots; i++) {
            int x = 8 + (i % cols) * 18;
            int y = 18 + (i / cols) * 18;
            addSlotToContainer(new Slot(crate, i, x, y));
        }

        int playerY = 18 + rows * 18 + 14;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                addSlotToContainer(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, playerY + row * 18));
            }
        }
        for (int col = 0; col < 9; col++) {
            addSlotToContainer(new Slot(playerInventory, col, 8 + col * 18, playerY + 58));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return crate.isUsableByPlayer(playerIn);
    }
}
