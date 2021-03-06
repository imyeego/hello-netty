package com.imyeego.protocol.response;

import com.imyeego.protocol.Command;
import com.imyeego.protocol.Packet;
import lombok.Data;

@Data
public class LoginResponsePacket extends Packet {

    private String userId;

    private String userName;

    private boolean success;

    private String reason;
    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }
}
