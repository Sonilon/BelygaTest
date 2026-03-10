package com.belyga.decor.client;

import com.belyga.decor.tileentity.TileEntityDecorCrate;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public class RenderDecorObj extends TileEntitySpecialRenderer<TileEntityDecorCrate> {
    @Override
    public void render(TileEntityDecorCrate te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        if (te == null || te.getWorld() == null) {
            return;
        }
        int light = te.getWorld().getCombinedLight(te.getPos(), 0);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, light & 65535, light >> 16);
        // OBJ rendering should be handled here via baked model cache.
    }
}
