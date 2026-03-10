package com.belyga.decor.client;

import com.belyga.decor.common.CommonProxy;
import com.belyga.decor.tileentity.TileEntityDecorCrate;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {
    @Override
    public void preInit() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDecorCrate.class, new RenderDecorObj());
    }
}
