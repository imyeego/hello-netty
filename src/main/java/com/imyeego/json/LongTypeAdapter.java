package com.imyeego.json;

public class LongTypeAdapter implements TypeAdapter<Long> {
    @Override
    public Object read(Reader reader) {
        return reader.nextInt();
    }

    @Override
    public String write(Writer writer, Long value) {
        return writer.writeLong(value).getString();
    }
}
