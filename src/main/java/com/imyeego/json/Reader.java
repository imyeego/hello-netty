package com.imyeego.json;

public class Reader {

    private char[] buffer;
    private int pos;

    public Reader(String json) {
        buffer = json.toCharArray();
        pos = 0;
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
            }else {
                pos --;
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

    public void beginArray() {

    }

    public void endArray() {

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
        return nextString();
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
            if (c == '{' || c == ' ' || c == ','){
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
