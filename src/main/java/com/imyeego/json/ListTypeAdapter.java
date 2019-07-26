package com.imyeego.json;

import java.util.ArrayList;
import java.util.List;

public class ListTypeAdapter implements TypeAdapter<List<?>> {

    private Class<?> elementClazz;

    public ListTypeAdapter(Class<?> elementClazz) {
        this.elementClazz = elementClazz;
    }

    @Override
    public Object read(Reader reader) {
        List list = new ArrayList<>();
        ObjectTypeAdapter parser = new ObjectTypeAdapter(elementClazz);
        reader.beginArray();
        while (reader.hasNextJsonString()) {
            Object o = parser.read(reader);
            list.add(o);
        }

        reader.endArray();
        return list;
    }

    @Override
    public String write(Writer writer, List<?> value) {
        ObjectTypeAdapter parser = new ObjectTypeAdapter(elementClazz);
        writer.beginArray();
        for (Object object : value) {
            parser.write(writer, object);
            writer.nextArray();
        }
        writer.endArray();
        return writer.getString();
    }
}
