package com.belyga.decor.inventory;

import com.belyga.decor.gui.GuiDecorCrate;
import com.belyga.decor.tileentity.TileEntityDecorCrate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
    public static final int CRATE_GUI_ID = 0;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID != CRATE_GUI_ID) return null;
        TileEntity te = world.getTileEntity(new BlockPos(x, y, z));
        return te instanceof TileEntityDecorCrate ? new ContainerDecorCrate(player.inventory, (TileEntityDecorCrate) te) : null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID != CRATE_GUI_ID) return null;
        TileEntity te = world.getTileEntity(new BlockPos(x, y, z));
        return te instanceof TileEntityDecorCrate ? new GuiDecorCrate(player.inventory, (TileEntityDecorCrate) te) : null;
    }
}
