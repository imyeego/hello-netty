package com.imyeego.json;

public interface TypeAdapter {
    Object read(Reader reader);
    String write(Writer writer);
}
