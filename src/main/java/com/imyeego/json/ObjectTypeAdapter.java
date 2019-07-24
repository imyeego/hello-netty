package com.imyeego.json;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;

public class ObjectTypeAdapter implements TypeAdapter<Object> {

    private static final int START_TAG = -1;
    private static final int OBJECT = 0;
    private static final int NAME = 1;
    private static final int VALUE = 2;

    private Class<?> raw;
    private Map<String, Field> map; // <fieldName, typeName>
    private Stack<Integer> stack;
    private Stack<String> nameStack;

    public ObjectTypeAdapter(Class<?> raw) {
        this.raw = raw;
        map = new HashMap<>();
        stack = new Stack<>();
        nameStack = new Stack<>();
    }

    @Override
    public Object read(Reader reader) {
        getFields();

        Object instance;
        try {
            instance = raw.newInstance();
        } catch (Exception e) {
            return null;
        }

        stack.push(START_TAG);
        while (reader.hasNext() && !stack.empty()){
            if (stack.peek() == START_TAG){
                stack.pop();
            }
            char c;
            try {
                c = reader.next();
            } catch (Reader.BufferException e) {
                e.printStackTrace();
                break ;
            }
            reader.skipNext();

//            Logger.d("c is " + c);
            switch (c){
                case '{':
                    if (stack != null && !stack.isEmpty() && stack.peek() == VALUE){
                        reader.moveToLast();
                        setValue(reader, instance);
                    }else {
                        stack.push(OBJECT);
                        stack.push(NAME);
                    }
                    continue;
                case '"':
                    setNameAndValue(reader, instance);
                    break;
                case ',':
                    stack.push(NAME);
                    break;
                case ':':
                    stack.push(VALUE);
                    break;
                case '}':
                    if (stack.peek() == OBJECT){
                        stack.pop();
                        reader.moveToLast();
                    }
                    break;
                case ' ':
                case '\n':
                case '\r':
                case '\t':
                    break;
                default:
                    reader.moveToLast();
                    setNameAndValue(reader, instance);
                    break;
            }
        }

        return instance;
    }

    @Override
    public String write(Writer writer, Object value) {
        Field[] fields = raw.getDeclaredFields();
        writer.startObject();
        for (Field field : fields) {
            writeNameAndValue(writer, field, value);
        }
        writer.toPrevious();
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
            TypeAdapter p = TypeAdapterFactory.getAdapterFromType(type);
            if (p != null) {
                p.write(writer, object);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            field.setAccessible(false);
        }

    }

    private void setValue(Reader reader, Object instance) {
        if (nameStack.isEmpty()){
            return;
        }
        String name = nameStack.pop();
        Field field = map.get(name);
        if (field != null){
            TypeAdapter p;
            String type = field.getType().getName();

            p = TypeAdapterFactory.getAdapterFromType(type);
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
        stack.pop();
    }

    private void setNameAndValue(Reader reader, Object instance) {
        switch (stack.peek()) {
            case NAME:
                String name = reader.nextName();
//                Logger.d("name is " + name);
                Field field = map.get(name);
                if (field != null){
                    nameStack.push(name);
                }
                stack.pop();
                break;
            case VALUE:
                setValue(reader, instance);
                break;
        }

    }
}
