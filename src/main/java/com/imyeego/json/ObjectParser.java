package com.imyeego.json;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ObjectParser implements Parser {

    private static final int START_TAG = -1;
    private static final int OBJECT = 0;
    private static final int NAME = 1;
    private static final int VALUE = 2;

    private Class<?> raw;
    private Map<String, Field> map; // <fieldName, typeName>
    private Stack<Integer> stack;
    private Stack<String> nameStack;

    public ObjectParser(Class<?> raw) {
        this.raw = raw;
        map = new HashMap<>();
        stack = new Stack<>();
        nameStack = new Stack<>();
    }

    @Override
    public Object parse(Reader reader) {
        Field[] fields = raw.getDeclaredFields();
        for (Field f : fields){
            String name = f.getName();
            map.put(name, f);
        }

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

    private void setValue(Reader reader, Object instance) {
        if (nameStack.isEmpty()){
            return;
        }
        String name = nameStack.pop();
        Field field = map.get(name);
        if (field != null){
            Parser p;
            String type = field.getType().getName();

            p = ParserFactory.getParserFromType(type);
            Object obj = p.parse(reader);
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
