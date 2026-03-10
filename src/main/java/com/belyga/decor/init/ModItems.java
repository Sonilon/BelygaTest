package com.belyga.decor.init;

import com.belyga.decor.BelygaDecorMod;
import com.belyga.decor.item.ItemRotationTool;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = BelygaDecorMod.MOD_ID)
public class ModItems {
    public static final Item ROTATION_TOOL = new ItemRotationTool().setRegistryName(BelygaDecorMod.MOD_ID, "rotation_tool").setUnlocalizedName("rotation_tool").setCreativeTab(net.minecraft.creativetab.CreativeTabs.TOOLS);

    public static void init() {
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(ROTATION_TOOL);
    }
}
