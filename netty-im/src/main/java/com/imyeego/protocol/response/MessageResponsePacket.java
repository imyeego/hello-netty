package com.imyeego.protocol.response;

import com.imyeego.protocol.Command;
import com.imyeego.protocol.Packet;
import lombok.Data;

@Data
public class MessageResponsePacket extends Packet {

    private String fromUserId;

    private String fromUserName;

    private String message;

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_RESPONSE;
    }
}
