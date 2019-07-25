package com.imyeego.json;

import java.util.Stack;

public class Reader {

    private static final int OBJECT = 0;
    private static final int ARRAY = 1;

    private char[] buffer;
    private int pos;
    private Stack<Integer> stack;
    private Stack<String> nameStack;

    public Reader(String json) {
        buffer = json.toCharArray();
        pos = 0;
        stack = new Stack<>();
        nameStack = new Stack<>();
    }

    public int nextInt() {
        String s = nextNumString();
        if (s == null || s.isEmpty()){
            return -1;
        }
        return Integer.valueOf(s);
    }

    public String nextNumString() {
        nextNonWhitespace();

        StringBuilder stringBuilder = new StringBuilder();
        char c;
        while (pos < buffer.length){
            c = buffer[pos ++];
            if (c == '"' && stringBuilder.length() <= 0){
                continue;
            }else if (c >= '0' && c <= '9' || c == '.' || c == '-'){
                stringBuilder.append(c);
            }else if (c == ','){
//                pos --;
                break;
            }
        }
        return stringBuilder.toString();
    }

    public String nextString() {
        nextNonWhitespace();

        StringBuilder stringBuilder = new StringBuilder();
        char c;
        while (pos < buffer.length){
            c = buffer[pos ++];
            if (c == '"' && stringBuilder.length() <= 0){
                continue;
            }else if (c == '"' || c == '}' || c == ','){
                break;
            }else {
                stringBuilder.append(c);
            }
        }
        return stringBuilder.toString();
    }

    public boolean nextBoolean() {
        String s = nextString();
        s = s.toUpperCase();
        if (s.equals("T") || s.equals("TRUE") ){
            return true;
        }
        return false;
    }

    public boolean hasNextName() {
        nextNonWhitespace();
        StringBuilder stringBuilder = new StringBuilder();
        char c;
        while (pos < buffer.length) {
            c = buffer[pos ++];
            if (c == ':') break;
            if (c == '}') {
                pos --;
                break;
            }
            switch (c) {
                case '"':
                    if (stringBuilder.length() == 0) {
                        continue;
                    }
                    break;
                case ' ':
                case '\n':
                case '\t':
                case '\r':
                case '{':
                    break;
                default:
                    stringBuilder.append(c);
            }
        }
        if (stringBuilder.length() > 0) nameStack.push(stringBuilder.toString());
        return !nameStack.isEmpty();
    }

    public void beginObject() {
        if (buffer[pos] == '{') {
            stack.push(OBJECT);
            pos ++;
        }

    }

    public void endObject() {
        if (buffer[pos] == '}') {
            stack.pop();
        }
    }

    public void beginArray() {
        if (buffer[pos] == '[') {
            stack.push(ARRAY);
            pos ++;
        }
    }

    public void endArray() {
        if (buffer[pos] == ']') {
            stack.pop();
        }
    }

    public boolean hasNextJsonString() {
        nextNonWhitespace();
        StringBuilder stringBuilder = new StringBuilder();
        int length = 0;
        char c;
        while (pos < buffer.length) {
            c = buffer[pos ++];
            if ((c == '}'|| c == ',') && stack.peek() != OBJECT) {
                continue;
            }
            if (c == '{') {
                stack.push(OBJECT);
            }
            if (c == ']') {
                pos --;
                break;
            }
            length ++;
            if (c == '}' && stack.peek() == OBJECT) {
                stack.pop();
                break;
            }
        }
        if (length > 0) {
            pos -= length;
        }
        return length > 0 && stack.peek() == ARRAY;
    }

    public double nextDouble() {
        String s = nextNumString();
        return Double.valueOf(s);
    }

    public float nextFloat() {
        String s = nextString();
        return Float.valueOf(s);
    }

    public String nextName() {
        return nameStack.pop();
    }

    public boolean hasNext() {
        return pos < buffer.length;
    }

    public char next() throws BufferException{
        if (pos < buffer.length){
            return buffer[pos];
        }
        throw new BufferException("no next");
    }

    public void skipNext() {
        pos ++;
    }

    public void moveToLast() {
        pos --;
    }

    public void nextNonWhitespace() {
        while (pos < buffer.length){
            char c = buffer[pos];
            if (c == ' ' || c == ','){
                pos ++;
                continue;
            }else {
                break;
            }
        }
    }

    public static class BufferException extends Exception {

        public BufferException(String message) {
            super(message);
        }

        public BufferException() {
            super();
        }
    }
}
