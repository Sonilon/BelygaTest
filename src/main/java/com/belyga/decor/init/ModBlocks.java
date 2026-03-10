package com.belyga.decor.init;

import com.belyga.decor.BelygaDecorMod;
import com.belyga.decor.block.BlockDecorCrate;
import com.belyga.decor.block.BlockDecorLamp;
import com.belyga.decor.block.DecorDefinition;
import com.belyga.decor.tileentity.TileEntityDecorCrate;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = BelygaDecorMod.MOD_ID)
public class ModBlocks {
    public static final List<Block> BLOCKS = new ArrayList<>();

    public static void init() {
        addCrate(7, 20); addCrate(8, 5); addCrate(9, 3); addCrate(10, 2); addCrate(11, 2); addCrate(12, 5);
        addCrate(13, 3); addCrate(14, 3); addCrate(15, 2); addCrate(16, 2); addCrate(17, 4); addCrate(18, 4);
        addCrate(19, 4); addCrate(20, 5); addCrate(29, 23); addCrate(30, 10); addCrate(31, 1);

        addLamp(21, 15, false); addLamp(22, 11, false); addLamp(23, 13, false); addLamp(24, 13, false);
        addLamp(25, 13, true); addLamp(26, 16, false); addLamp(27, 13, false); addLamp(28, 16, false); addLamp(33, 6, false);

        GameRegistry.registerTileEntity(TileEntityDecorCrate.class, BelygaDecorMod.MOD_ID + ":decor_crate_te");
    }

    private static void addCrate(int folder, int slots) {
        BlockDecorCrate block = new BlockDecorCrate(DecorDefinition.crate(folder, slots));
        block.setRegistryName(BelygaDecorMod.MOD_ID, "crate_" + folder);
        block.setUnlocalizedName("crate_" + folder);
        block.setCreativeTab(net.minecraft.creativetab.CreativeTabs.DECORATIONS);
        BLOCKS.add(block);
    }

    private static void addLamp(int folder, int light, boolean toggleable) {
        BlockDecorLamp block = new BlockDecorLamp(DecorDefinition.lamp(folder, light, toggleable));
        block.setRegistryName(BelygaDecorMod.MOD_ID, "lamp_" + folder);
        block.setUnlocalizedName("lamp_" + folder);
        block.setCreativeTab(net.minecraft.creativetab.CreativeTabs.DECORATIONS);
        BLOCKS.add(block);
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        BLOCKS.forEach(event.getRegistry()::register);
    }

    @SubscribeEvent
    public static void registerItemBlocks(RegistryEvent.Register<net.minecraft.item.Item> event) {
        for (Block block : BLOCKS) {
            event.getRegistry().register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
        }
    }
}
