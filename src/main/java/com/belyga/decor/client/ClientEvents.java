package com.belyga.decor.client;

import com.belyga.decor.network.NetworkHandler;
import com.belyga.decor.network.PacketOpenCrate;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClientEvents {
    private static BlockPos pendingCrate;
    private static long startTime;
    private static final int DELAY_MS = 1500;

    public static void openDelayedCrate(BlockPos pos) {
        pendingCrate = pos;
        startTime = System.currentTimeMillis();
    }

    @SubscribeEvent
    public void onOverlay(RenderGameOverlayEvent.Post event) {
        if (pendingCrate == null || event.getType() != RenderGameOverlayEvent.ElementType.ALL) {
            return;
        }
        Minecraft mc = Minecraft.getMinecraft();
        ScaledResolution sr = new ScaledResolution(mc);
        int w = 220;
        int h = 18;
        int x = (sr.getScaledWidth() - w) / 2;
        int y = sr.getScaledHeight() - 60;

        long elapsed = System.currentTimeMillis() - startTime;
        float progress = Math.min(1F, elapsed / (float) DELAY_MS);

        Gui.drawRect(x, y, x + w, y + h, 0xAA101010);
        Gui.drawRect(x + 2, y + 2, x + 2 + (int) ((w - 4) * progress), y + h - 2, 0xFFFF7A1A);
        mc.fontRenderer.drawStringWithShadow("Открытие ящика", x + 8, y - 12, 0xFFFFFF);

        if (progress >= 1F) {
            NetworkHandler.CHANNEL.sendToServer(new PacketOpenCrate(pendingCrate));
            pendingCrate = null;
        }
    }
}
