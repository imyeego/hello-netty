package com.imyeego.protocol.request;

import com.imyeego.protocol.Command;
import com.imyeego.protocol.Packet;
import lombok.Data;

@Data
public class LoginRequestPacket extends Packet {

    private String userName;

    private String password;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
