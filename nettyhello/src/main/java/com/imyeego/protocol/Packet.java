package com.imyeego.protocol;

import com.alibaba.fastjson.annotation.JSONField;

public abstract class Packet {
    @JSONField(serialize = false, deserialize = false)
    private Byte version = 1;


    public Byte getVersion() {
        return version;
    }

    public void setVersion(Byte version) {
        this.version = version;
    }

    @JSONField(serialize = false)
    public abstract Byte getCommand();
}
