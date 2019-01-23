package com.imyeego.handler;

import io.netty.channel.ChannelHandlerContext;

public interface DataHandler<T> {
    void handle(T t, ChannelHandlerContext ctx);
}
