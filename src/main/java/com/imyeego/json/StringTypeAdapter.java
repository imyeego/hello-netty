package com.imyeego.json;

public class StringTypeAdapter implements TypeAdapter<String> {
    @Override
    public Object read(Reader reader) {
        return reader.nextString();
    }

    @Override
    public String write(Writer writer, String value) {
        return writer.writeString(value).getString();
    }
}
