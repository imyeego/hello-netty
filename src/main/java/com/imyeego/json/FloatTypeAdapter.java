package com.imyeego.json;

public class FloatTypeAdapter implements TypeAdapter {
    @Override
    public Object read(Reader reader) {
        return reader.nextFloat();
    }

    @Override
    public String write(Writer writer) {
        return null;
    }
}
