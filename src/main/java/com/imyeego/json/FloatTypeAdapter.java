package com.imyeego.json;

public class FloatTypeAdapter implements TypeAdapter<Float> {
    @Override
    public Object read(Reader reader) {
        return reader.nextFloat();
    }

    @Override
    public String write(Writer writer, Float value) {
        return writer.writeFloat(value).getString();
    }
}
