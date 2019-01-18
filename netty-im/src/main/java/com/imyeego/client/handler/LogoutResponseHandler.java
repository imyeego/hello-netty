package com.imyeego.client.handler;

import com.imyeego.protocol.request.LogoutRequestPacket;
import com.imyeego.utils.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class LogoutResponseHandler extends SimpleChannelInboundHandler<LogoutRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutRequestPacket msg) {
        SessionUtil.unBindSession(ctx.channel());
    }
}
