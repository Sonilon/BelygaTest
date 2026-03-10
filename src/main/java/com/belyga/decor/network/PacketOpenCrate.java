package com.belyga.decor.network;

import com.belyga.decor.inventory.GuiHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketOpenCrate implements IMessage {
    private BlockPos pos;

    public PacketOpenCrate() {}

    public PacketOpenCrate(BlockPos pos) {
        this.pos = pos;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        pos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(pos.getX());
        buf.writeInt(pos.getY());
        buf.writeInt(pos.getZ());
    }

    public static class Handler implements IMessageHandler<PacketOpenCrate, IMessage> {
        @Override
        public IMessage onMessage(PacketOpenCrate message, MessageContext ctx) {
            EntityPlayerMP player = ctx.getServerHandler().player;
            player.getServerWorld().addScheduledTask(() -> player.openGui(com.belyga.decor.BelygaDecorMod.INSTANCE, GuiHandler.CRATE_GUI_ID, player.world, message.pos.getX(), message.pos.getY(), message.pos.getZ()));
            return null;
        }
    }
}
