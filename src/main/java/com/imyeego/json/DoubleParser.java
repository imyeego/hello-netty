package com.imyeego.json;

public class DoubleParser implements Parser {
    @Override
    public Object parse(Reader reader) {
        return reader.nextDouble();
    }
}
