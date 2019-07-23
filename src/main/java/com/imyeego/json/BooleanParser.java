package com.imyeego.json;

public class BooleanParser implements Parser {
    @Override
    public Object parse(Reader reader) {
        return reader.nextBoolean();
    }
}
