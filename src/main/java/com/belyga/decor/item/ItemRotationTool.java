package com.belyga.decor.item;

import com.belyga.decor.tileentity.TileEntityDecorCrate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemRotationTool extends Item {
    private static final float[] STEPS = new float[] {30F, 45F, 60F};

    public ItemRotationTool() {
        setMaxStackSize(1);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack stack = player.getHeldItem(hand);
        if (player.isSneaking()) {
            cycleStep(stack);
            return EnumActionResult.SUCCESS;
        }
        TileEntity te = worldIn.getTileEntity(pos);
        if (te instanceof TileEntityDecorCrate && !worldIn.isRemote) {
            ((TileEntityDecorCrate) te).rotateBy(getStep(stack));
            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.PASS;
    }

    private void cycleStep(ItemStack stack) {
        NBTTagCompound tag = stack.getOrCreateSubCompound("rotation");
        int idx = (tag.getInteger("idx") + 1) % STEPS.length;
        tag.setInteger("idx", idx);
    }

    private float getStep(ItemStack stack) {
        NBTTagCompound tag = stack.getSubCompound("rotation");
        int idx = tag == null ? 0 : tag.getInteger("idx");
        return STEPS[Math.max(0, Math.min(STEPS.length - 1, idx))];
    }
}
