package com.imyeego.json;

public class StringParser implements Parser {
    @Override
    public Object parse(Reader reader) {
        return reader.nextString();
    }
}
