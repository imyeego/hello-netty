package com.imyeego.json;

public interface TypeAdapter<T> {
    Object read(Reader reader);
    String write(Writer writer, T value);
}
