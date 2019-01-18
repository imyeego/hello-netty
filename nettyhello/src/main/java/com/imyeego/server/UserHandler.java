package com.imyeego.server;

import com.imyeego.protobuf.User;
import com.imyeego.protobuf.UserEntity;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.net.InetAddress;
import java.util.Date;

@ChannelHandler.Sharable
public class UserHandler extends ChannelInboundHandlerAdapter {
    public static final UserHandler INSTANCE = new UserHandler();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 为新连接发送庆祝
        User user = User.newBuilder().setId(0).setName("hello").build();

        ctx.writeAndFlush(user);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String response;
        if (msg != null && msg instanceof User){
            User user = (User) msg;
            response = user.toString();
            System.out.println(response);
            User resUser = User.newBuilder().setId(2).setName("Sha").build();
            ctx.writeAndFlush(resUser).addListener(future -> {
                if (future.isDone()){
                    System.out.println("发送成功...");
                }
            });
        } else if (msg == null){
            System.out.println("上传对象为空...");
        }
    }

    /**
     * 异常处理
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
