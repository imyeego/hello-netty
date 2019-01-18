package com.imyeego.protocol.response;

import com.imyeego.protocol.Command;
import com.imyeego.protocol.Packet;
import lombok.Data;

@Data
public class LogoutResponsePacket extends Packet {
    private boolean success;

    private String reason;
    @Override
    public Byte getCommand() {
        return Command.LOGOUT_RESPONSE;
    }
}
