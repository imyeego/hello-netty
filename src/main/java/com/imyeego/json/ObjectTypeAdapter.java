package com.imyeego.json;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;

public class ObjectTypeAdapter implements TypeAdapter<Object> {

    private Class<?> raw;
    private Map<String, Field> map; // <fieldName, Field>

    public ObjectTypeAdapter(Class<?> raw) {
        this.raw = raw;
        map = new HashMap<>();
        getFields();

    }

    @Override
    public Object read(Reader reader) {
        Object instance;
        try {
            instance = raw.newInstance();
        } catch (Exception e) {
            return null;
        }
        reader.beginObject();
        while (reader.hasNextName()) {
            setFieldByName(reader, instance);
        }
        reader.endObject();

        return instance;
    }

    @Override
    public String write(Writer writer, Object value) {
        Field[] fields = raw.getDeclaredFields();
        writer.startObject();
        for (Field field : fields) {
            writeNameAndValue(writer, field, value);
        }
        writer.endObject();

        return writer.getString();
    }

    private void getFields() {
        Field[] fields = raw.getDeclaredFields();
        for (Field f : fields) {
            String name = f.getName();
            map.put(name, f);
        }
    }

    @SuppressWarnings("unchecked")
    private void writeNameAndValue(Writer writer, Field field, Object src) {
        writer.writeName(field.getName());
        String type = field.getType().getName();
        Object object;
        try {
            field.setAccessible(true);
            object = field.get(src);
            TypeAdapter p;
            if (type.equals("java.util.List")) {
                String elementType = ((ParameterizedType)field.getGenericType()).getActualTypeArguments()[0].getTypeName();
                p = TypeAdapterFactory.getAdapterFromType(type, elementType);
            } else
                p = TypeAdapterFactory.getAdapterFromType(type, null);
            if (p != null) {
                p.write(writer, object);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            field.setAccessible(false);
        }

    }

    private void setFieldByName(Reader reader, Object instance) {
        String name = reader.nextName();
        Field field = map.get(name);
        if (field != null){
            TypeAdapter p;
            String type = field.getType().getName();
            if (type.equals("java.util.List")) {
                String elementType = ((ParameterizedType)field.getGenericType()).getActualTypeArguments()[0].getTypeName();
                p = TypeAdapterFactory.getAdapterFromType(type, elementType);
            } else
                p = TypeAdapterFactory.getAdapterFromType(type, null);
            Object obj = null;
            if (p != null) {
                obj = p.read(reader);
            }
//            Logger.d("value is " + obj);
            try {
                field.setAccessible(true);
                field.set(instance, obj);
                field.setAccessible(false);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }


}
