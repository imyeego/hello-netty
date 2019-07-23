package com.imyeego.json;

import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JsonParser {

    @SuppressWarnings("unchecked")
    public <T> T fromJson(String json, Class<T> clazz) {
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
    public <T> List<T> fromJsonList(@NotNull String json, Class<T> clazz) {
        Reader reader = new Reader(json);
        List<T> list = null;
        ObjectTypeAdapter parser = new ObjectTypeAdapter(clazz);
        char c;
        try {
            c = reader.next();
            if (c == '[' && reader.hasNext()) {
                reader.skipNext();
                list = new ArrayList<>();
                c = reader.next();
            }
        } catch (Reader.BufferException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
        while (c != ']') {
            if (c == '{') {
                Object obj = parser.read(reader);
                list.add((T) obj);
            }
            reader.skipNext();
            try {
                c = reader.next();
            } catch (Reader.BufferException e) {
                e.printStackTrace();
                return Collections.emptyList();
            }

        }

        return list;
    }
}
