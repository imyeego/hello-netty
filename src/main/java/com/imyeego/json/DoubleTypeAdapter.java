package com.imyeego.json;

public class DoubleTypeAdapter implements TypeAdapter<Double> {
    @Override
    public Object read(Reader reader) {
        return reader.nextDouble();
    }

    @Override
    public String write(Writer writer, Double value) {
        return writer.writeDouble(value).getString();
    }
}
