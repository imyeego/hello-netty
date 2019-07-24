package com.imyeego.json;

public class IntegerTypeAdapter implements TypeAdapter<Integer> {
    @Override
    public Object read(Reader reader) {
        return reader.nextInt();
    }

    @Override
    public String write(Writer writer, Integer value) {
        return writer.writeInteger(value).getString();
    }
}
