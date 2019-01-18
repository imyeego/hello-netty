package com.imyeego.protocol.response;

import com.imyeego.protocol.Command;
import com.imyeego.protocol.Packet;

public class HeartbeatResponsePacket extends Packet {

    @Override
    public Byte getCommand() {
        return Command.HEARTBEAT_RESPONSE;
    }
}
