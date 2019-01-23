package com.imyeego.handler;

import com.imyeego.protocol.Packet;
import io.netty.channel.ChannelHandlerContext;

public abstract class AbstractDataHandler<T extends Packet> implements DataHandler<T> {

    @Override
    public void handle(T t, ChannelHandlerContext ctx) {
        try {
            onEvent(t, ctx);
        } catch (Exception e) {
            exceptionCaught(e);
        }
    }

    abstract void onEvent(T t, ChannelHandlerContext ctx) throws Exception;

    void exceptionCaught(Throwable cause) {
        cause.printStackTrace();
    }
}