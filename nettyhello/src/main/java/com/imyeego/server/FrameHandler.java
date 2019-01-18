package com.imyeego.server;

import com.google.protobuf.MessageLite;
import com.imyeego.protobuf.TextMessage;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

@ChannelHandler.Sharable
public class FrameHandler extends ChannelInboundHandlerAdapter {

    public final static FrameHandler INSTANCE = new FrameHandler();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        TextMessage message = TextMessage.newBuilder().setText("I'm from netty side").build();
        ctx.writeAndFlush(message);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg != null && msg instanceof MessageLite){
            MessageLite frame = (MessageLite)msg;
            System.out.println(frame.getClass().getSimpleName());

        }
    }
}
