package com.belyga.decor;

import com.belyga.decor.client.ClientEvents;
import com.belyga.decor.common.CommonProxy;
import com.belyga.decor.init.ModBlocks;
import com.belyga.decor.init.ModItems;
import com.belyga.decor.inventory.GuiHandler;
import com.belyga.decor.network.NetworkHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod(modid = BelygaDecorMod.MOD_ID, name = BelygaDecorMod.NAME, version = BelygaDecorMod.VERSION)
public class BelygaDecorMod {
    public static final String MOD_ID = "belygadecor";
    public static final String NAME = "Belyga Decorative Blocks";
    public static final String VERSION = "1.0.0";

    @Mod.Instance(MOD_ID)
    public static BelygaDecorMod INSTANCE;

    @SidedProxy(clientSide = "com.belyga.decor.client.ClientProxy", serverSide = "com.belyga.decor.common.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        NetworkHandler.init();
        ModBlocks.init();
        ModItems.init();
        NetworkRegistry.INSTANCE.registerGuiHandler(INSTANCE, new GuiHandler());
        proxy.preInit();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init();
        if (event.getSide().isClient()) {
            MinecraftForge.EVENT_BUS.register(new ClientEvents());
        }
    }
}
