package com.imyeego.json;

public class IntegerParser implements Parser {
    @Override
    public Object parse(Reader reader) {
        return reader.nextInt();
    }
}
