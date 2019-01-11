package com.imyeego.codec;

import com.imyeego.protocol.Packet;
import com.imyeego.protocol.PacketCodec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;


public class PacketEncoder extends MessageToByteEncoder<Packet> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out) {
        PacketCodec.INSTANCE.encode(out, msg);
    }
}
