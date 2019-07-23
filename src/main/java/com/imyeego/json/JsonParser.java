package com.imyeego.json;

import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class JsonParser {

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
            ObjectParser objectParser = new ObjectParser(clazz);
            Object obj = objectParser.parse(reader);
            return (T) obj;
        }
        return null;
    }

    public <T> List<T> fromJsonList(@NotNull String json, Class<T> clazz) {
        Reader reader = new Reader(json);
        List<T> list = null;
        ObjectParser parser = new ObjectParser(clazz);
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
                Object obj = parser.parse(reader);
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
