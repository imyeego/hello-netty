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
        getFields();
        writer.startObject();
        Iterator<Map.Entry<String, Field>> it =  map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Field> entry = it.next();
            Field field = entry.getValue();
            try {
                field.setAccessible(true);
                writeNameAndValue(writer, entry.getKey(), field.get(value));
                field.setAccessible(false);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
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
    private void writeNameAndValue(Writer writer, String name, Object object) {
        String type = object.getClass().getName();
        writer.writeName(name);
        if (!type.isEmpty()) {
            TypeAdapter p = TypeAdapterFactory.getAdapterFromType(type);
            p.write(writer, object);
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
            Object obj = p.read(reader);
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
        if (stack.peek() == NAME){
            String name = reader.nextName();
//            Logger.d("name is " + name);
            Field field = map.get(name);
            if (field != null){
                nameStack.push(name);
            }
            stack.pop();
        }else if (stack.peek() == VALUE){
            setValue(reader, instance);
        }
    }
}
