package com.imyeego.handler;

import com.google.protobuf.MessageLite;
import com.imyeego.protobuf.Frame;
import com.imyeego.util.ParseFrameUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.util.List;

public class SecondProtobufCodec extends MessageToMessageCodec<Frame, MessageLite> {

    @Override
    protected void encode(ChannelHandlerContext ctx, MessageLite msg, List<Object> out) throws Exception {
        out.add(Frame.newBuilder()
                .setMessageType(msg.getClass().getSimpleName())
                .setPayload(msg.toByteString())
                .build());
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, Frame msg, List<Object> out) throws Exception {
        out.add(ParseFrameUtil.parse(msg));
    }
}
