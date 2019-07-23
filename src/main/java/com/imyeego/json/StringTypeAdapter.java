package com.imyeego.json;

public class StringTypeAdapter implements TypeAdapter {
    @Override
    public Object read(Reader reader) {
        return reader.nextString();
    }

    @Override
    public String write(Writer writer) {
        return null;
    }
}
