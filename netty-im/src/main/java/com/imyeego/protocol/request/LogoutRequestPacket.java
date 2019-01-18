package com.imyeego.protocol.request;

import com.imyeego.protocol.Command;
import com.imyeego.protocol.Packet;
import lombok.Data;

@Data
public class LogoutRequestPacket extends Packet {

    @Override
    public Byte getCommand() {
        return Command.LOGOUT_REQUEST;
    }
}
