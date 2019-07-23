package com.imyeego.json;

public class IntegerTypeAdapter implements TypeAdapter {
    @Override
    public Object read(Reader reader) {
        return reader.nextInt();
    }

    @Override
    public String write(Writer writer) {
        return null;
    }
}
