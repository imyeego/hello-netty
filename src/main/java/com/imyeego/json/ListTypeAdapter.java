package com.imyeego.json;

import java.util.List;

public class ListTypeAdapter implements TypeAdapter<List<?>> {

    private Class<?> elementClazz;

    public ListTypeAdapter(Class<?> elementClazz) {
        this.elementClazz = elementClazz;
    }

    @Override
    public Object read(Reader reader) {
        return null;
    }

    @Override
    public String write(Writer writer, List<?> value) {
        return null;
    }
}
