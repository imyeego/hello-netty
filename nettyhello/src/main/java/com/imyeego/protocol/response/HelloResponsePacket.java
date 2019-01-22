package com.imyeego.protocol.response;

import com.imyeego.protocol.Command;
import com.imyeego.protocol.Packet;
import lombok.Data;

@Data
public class HelloResponsePacket extends Packet {

    private String channelId;

    private String message;

    @Override
    public Byte getCommand() {
        return Command.HELLO_RESPONSE;
    }
}
