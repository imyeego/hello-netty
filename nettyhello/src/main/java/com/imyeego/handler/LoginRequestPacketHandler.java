package com.imyeego.handler;

import com.imyeego.annotation.HandlerMapping;
import com.imyeego.protocol.request.LoginRequestPacket;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author liuzhao
 * @version 1.0
 * @since 2019/1/23
 */
@HandlerMapping("LoginRequestPacket")
public class LoginRequestPacketHandler extends AbstractDataHandler<LoginRequestPacket> {
    @Override
    public void onEvent(LoginRequestPacket loginRequestPacket, ChannelHandlerContext ctx) throws Exception {

    }
}