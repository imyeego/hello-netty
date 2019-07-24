package com.imyeego.json;

public class BooleanTypeAdapter implements TypeAdapter<Boolean> {
    @Override
    public Object read(Reader reader) {
        return reader.nextBoolean();
    }

    @Override
    public String write(Writer writer, Boolean value) {
        return writer.writeBoolean(value).getString();

    }
}
