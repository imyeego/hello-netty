package com.imyeego.json;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JsonParser {

    @SuppressWarnings("unchecked")
    public <T> T fromJson(String json, Class<T> clazz) {
        if (json == null) return null;
        Reader reader = new Reader(json);
        char c;
        try {
            c = reader.next();
        } catch (Reader.BufferException e) {
            e.printStackTrace();
            return null;
        }
        if (reader.hasNext() && c != '['){
            ObjectTypeAdapter objectParser = new ObjectTypeAdapter(clazz);
            Object obj = objectParser.read(reader);
            return (T) obj;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> fromJsonList(String json, Class<T> clazz) {
        if (json == null) {
            return Collections.EMPTY_LIST;
        }
        Reader reader = new Reader(json);
        List<T> list = new ArrayList<>();
        ObjectTypeAdapter parser = new ObjectTypeAdapter(clazz);
        reader.beginArray();
        while (reader.hasNextJsonString()) {
            Object o = parser.read(reader);
            list.add((T) o);
        }

        reader.endArray();
        return list;
    }

    public String toJson(Object src) {
        if (src == null) {
            return "";
        }
        ObjectTypeAdapter adapter = new ObjectTypeAdapter(src.getClass());
        Writer writer = new Writer();
        return adapter.write(writer, src);
    }


}
