package com.imyeego.json;

public class FloatParser implements Parser {
    @Override
    public Object parse(Reader reader) {
        return reader.nextFloat();
    }
}
