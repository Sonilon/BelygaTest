package com.belyga.decor.network;

import com.belyga.decor.BelygaDecorMod;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkHandler {
    public static final SimpleNetworkWrapper CHANNEL = NetworkRegistry.INSTANCE.newSimpleChannel(BelygaDecorMod.MOD_ID);

    public static void init() {
        CHANNEL.registerMessage(PacketOpenCrate.Handler.class, PacketOpenCrate.class, 0, Side.SERVER);
    }
}
