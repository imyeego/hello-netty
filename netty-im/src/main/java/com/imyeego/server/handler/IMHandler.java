package com.imyeego.server.handler;

import com.imyeego.protocol.Packet;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.HashMap;
import java.util.Map;

import static com.imyeego.protocol.Command.LOGOUT_REQUEST;
import static com.imyeego.protocol.Command.MESSAGE_REQUEST;

@ChannelHandler.Sharable
public class IMHandler extends SimpleChannelInboundHandler<Packet> {

    public final static IMHandler INSTANCE = new IMHandler();

    private Map<Byte, SimpleChannelInboundHandler<? extends Packet>> handlerMap;

    private IMHandler(){
        handlerMap = new HashMap<>();
        handlerMap.put(MESSAGE_REQUEST, MessageRequestHandler.INSTANCE);
        handlerMap.put(LOGOUT_REQUEST, LogoutRequestHandler.INSTANCE);

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet packet) throws Exception{
        handlerMap.get(packet.getCommand()).channelRead(ctx, packet);
    }
}
