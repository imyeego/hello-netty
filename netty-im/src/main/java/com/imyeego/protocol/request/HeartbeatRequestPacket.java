package com.imyeego.protocol.request;

import com.imyeego.protocol.Command;
import com.imyeego.protocol.Packet;

public class HeartbeatRequestPacket extends Packet {

    @Override
    public Byte getCommand() {
        return Command.HEARTBEAT_REQUEST;
    }
}
