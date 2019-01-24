package com.imyeego.server;

import com.imyeego.protocol.Packet;
import com.imyeego.protocol.request.LoginRequestPacket;
import com.imyeego.protocol.response.HelloResponsePacket;
import com.imyeego.protocol.response.LoginResponsePacket;
import com.imyeego.util.HandlerUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.net.InetAddress;
import java.nio.charset.Charset;
import java.util.UUID;

@ChannelHandler.Sharable
public class JSONHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        HelloResponsePacket packet = new HelloResponsePacket();
        packet.setChannelId(UUID.randomUUID().toString().split("-")[0]);
        packet.setMessage("Welcome! Just enjoy me...");
        ctx.writeAndFlush(packet);

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg != null && msg instanceof Packet){
            HandlerUtil.getHandlerInstance(msg.getClass().getSimpleName()).handle(msg, ctx);

        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
