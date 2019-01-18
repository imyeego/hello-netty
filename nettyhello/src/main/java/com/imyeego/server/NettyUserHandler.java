package com.imyeego.server;

import com.imyeego.protobuf.User;
import com.imyeego.protobuf.UserEntity;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.net.InetAddress;
import java.util.Date;

@ChannelHandler.Sharable
public class NettyUserHandler extends SimpleChannelInboundHandler<User> {

    public static final NettyUserHandler INSTANCE = new NettyUserHandler();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        // 为新连接发送庆祝
        ctx.write("Welcome to " + InetAddress.getLocalHost().getHostName() + "!\r\n");
        ctx.write("It is " + new Date() + " now.\r\n");
        ctx.flush();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, User msg) {
        String response;
        if (msg != null) {
            response = msg.toString();
            System.out.println(response);
            ChannelFuture future = ctx.write(response);
        } else {
            System.out.println("上传对象为空");
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
