package com.imyeego.json;

public class DoubleTypeAdapter implements TypeAdapter {
    @Override
    public Object read(Reader reader) {
        return reader.nextDouble();
    }

    @Override
    public String write(Writer writer) {
        return null;
    }
}
