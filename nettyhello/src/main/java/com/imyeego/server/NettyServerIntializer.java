package com.imyeego.server;

import com.imyeego.handler.SecondProtobufCodec;
import com.imyeego.protobuf.Frame;
import com.imyeego.protobuf.UserEntity;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

public class NettyServerIntializer extends ChannelInitializer<SocketChannel>{

    private static final ProtobufDecoder DECODER = new ProtobufDecoder(Frame.getDefaultInstance());
    private static final ProtobufEncoder ENCODER = new ProtobufEncoder();

    @Override
    protected void initChannel(SocketChannel ch) {
        ChannelPipeline ph = ch.pipeline();

        //入参说明: 读超时时间、写超时时间、所有类型的超时时间、时间格式
//        ph.addLast(new IdleStateHandler(5, 0, 0, TimeUnit.SECONDS));
        // 解码和编码，应和客户端一致
        //传输的协议 Protobuf
//        ph.addLast(new ProtobufVarint32FrameDecoder());
        ph.addLast(DECODER);
//        ph.addLast(new ProtobufVarint32LengthFieldPrepender());
        ph.addLast(ENCODER);

        ph.addLast(new SecondProtobufCodec());
        ph.addLast(FrameHandler.INSTANCE);
    }
}
