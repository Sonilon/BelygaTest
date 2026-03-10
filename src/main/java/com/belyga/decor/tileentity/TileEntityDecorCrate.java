package com.belyga.decor.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class TileEntityDecorCrate extends TileEntity implements IInventory {
    private NonNullList<ItemStack> stacks;
    private float rotationY;

    public TileEntityDecorCrate() {
        this(0);
    }

    public TileEntityDecorCrate(int slots) {
        this.stacks = NonNullList.withSize(Math.max(1, slots), ItemStack.EMPTY);
    }

    public float getRotationY() {
        return rotationY;
    }

    public void rotateBy(float angle) {
        rotationY = (rotationY + angle) % 360F;
        markDirty();
    }

    public void setRotationY(float rotationY) {
        this.rotationY = rotationY;
        markDirty();
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setFloat("RotationY", rotationY);
        NBTTagList list = new NBTTagList();
        for (int i = 0; i < stacks.size(); i++) {
            if (!stacks.get(i).isEmpty()) {
                NBTTagCompound itemTag = new NBTTagCompound();
                itemTag.setByte("Slot", (byte) i);
                stacks.get(i).writeToNBT(itemTag);
                list.appendTag(itemTag);
            }
        }
        compound.setTag("Items", list);
        compound.setInteger("Size", stacks.size());
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        rotationY = compound.getFloat("RotationY");
        int size = Math.max(1, compound.getInteger("Size"));
        stacks = NonNullList.withSize(size, ItemStack.EMPTY);
        NBTTagList list = compound.getTagList("Items", 10);
        for (int i = 0; i < list.tagCount(); i++) {
            NBTTagCompound tag = list.getCompoundTagAt(i);
            int slot = tag.getByte("Slot") & 255;
            if (slot < stacks.size()) {
                stacks.set(slot, new ItemStack(tag));
            }
        }
    }

    @Override public int getSizeInventory() { return stacks.size(); }
    @Override public boolean isEmpty() { return stacks.stream().allMatch(ItemStack::isEmpty); }
    @Override public ItemStack getStackInSlot(int index) { return stacks.get(index); }
    @Override public ItemStack decrStackSize(int index, int count) {
        ItemStack stack = stacks.get(index);
        if (stack.isEmpty()) return ItemStack.EMPTY;
        ItemStack split = stack.splitStack(count);
        if (stack.getCount() <= 0) stacks.set(index, ItemStack.EMPTY);
        markDirty();
        return split;
    }
    @Override public ItemStack removeStackFromSlot(int index) { ItemStack s = stacks.get(index); stacks.set(index, ItemStack.EMPTY); return s; }
    @Override public void setInventorySlotContents(int index, ItemStack stack) { stacks.set(index, stack); }
    @Override public int getInventoryStackLimit() { return 64; }
    @Override public boolean isUsableByPlayer(EntityPlayer player) { return !isInvalid(); }
    @Override public void openInventory(EntityPlayer player) {}
    @Override public void closeInventory(EntityPlayer player) {}
    @Override public boolean isItemValidForSlot(int index, ItemStack stack) { return true; }
    @Override public int getField(int id) { return 0; }
    @Override public void setField(int id, int value) {}
    @Override public int getFieldCount() { return 0; }
    @Override public void clear() { for (int i = 0; i < stacks.size(); i++) stacks.set(i, ItemStack.EMPTY); }
    @Override public String getName() { return "decor_crate"; }
    @Override public boolean hasCustomName() { return false; }
    @Override public ITextComponent getDisplayName() { return new TextComponentString(getName()); }
}
