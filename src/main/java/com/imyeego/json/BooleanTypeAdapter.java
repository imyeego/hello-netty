package com.imyeego.json;

public class BooleanTypeAdapter implements TypeAdapter {
    @Override
    public Object read(Reader reader) {
        return reader.nextBoolean();
    }

    @Override
    public String write(Writer writer) {
        return null;
    }
}
