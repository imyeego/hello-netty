package com.imyeego.server.handler;

import com.imyeego.protocol.request.HeartbeatRequestPacket;
import com.imyeego.protocol.response.HeartbeatResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;


@ChannelHandler.Sharable
public class HeartbeatRequestHandler extends SimpleChannelInboundHandler<HeartbeatRequestPacket> {

    public static final HeartbeatRequestHandler INSTANCE = new HeartbeatRequestHandler();

    private HeartbeatRequestHandler(){}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HeartbeatRequestPacket msg) {
        ctx.writeAndFlush(new HeartbeatResponsePacket());
    }
}
