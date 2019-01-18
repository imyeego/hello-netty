package com.imyeego.client;

import com.imyeego.handler.SecondProtobufCodec;
import com.imyeego.protobuf.Frame;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class ClientInitializer extends ChannelInitializer<SocketChannel> {

//    private static final StringDecoder DECODER = new StringDecoder();
//    private static final StringEncoder ENCODER = new StringEncoder();

    private static final ClientHandler CLIENT_HANDLER = new ClientHandler();

    @Override
    protected void initChannel(SocketChannel ch) {
        ChannelPipeline pipeline = ch.pipeline();
//        pipeline.addLast(new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        pipeline.addLast(new ProtobufDecoder(Frame.getDefaultInstance()));
        pipeline.addLast(new ProtobufEncoder());

        pipeline.addLast(new SecondProtobufCodec());
        pipeline.addLast(CLIENT_HANDLER);
    }
}
