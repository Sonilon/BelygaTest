package com.belyga.decor.block;

import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockDecorLamp extends BlockDecorBase {
    public static final PropertyBool LIT = PropertyBool.create("lit");

    public BlockDecorLamp(DecorDefinition definition) {
        super(definition);
        setDefaultState(getDefaultState().withProperty(LIT, true));
        setLightLevel(Math.min(1F, (definition.light == null ? 0 : definition.light) / 15F));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING, ROTATION, LIT);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return super.getStateFromMeta(meta & 0x3F).withProperty(LIT, (meta & 64) == 0);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int m = super.getMetaFromState(state);
        if (!state.getValue(LIT)) {
            m |= 64;
        }
        return m;
    }

    @Override
    public int getLightValue(IBlockState state) {
        if (!state.getValue(LIT)) {
            return 0;
        }
        return definition.light == null ? 0 : definition.light;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!definition.toggleable) {
            return false;
        }
        if (!worldIn.isRemote) {
            worldIn.setBlockState(pos, state.cycleProperty(LIT), 3);
        }
        return true;
    }
}
