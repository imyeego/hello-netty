package com.imyeego.json;

public class ParserFactory {

    public static Parser getParserFromType(String type) {
        switch (type) {
            case "boolean":
            case "Java.lang.Boolean":
                return new BooleanParser();
            case "short":
            case "int":
            case "long":
            case "byte":
            case "java.lang.Long":
            case "java.lang.Integer":
            case "java.lang.Short":
            case "java.lang.Byte":
            case "java.math.BigInteger":
                return new IntegerParser();
            case "float":
            case "java.lang.Float":
                return new FloatParser();
            case "double":
            case "java.lang.Double":
            case "java.math.BigDecimal":
                return new DoubleParser();
            case "java.lang.String":
                return new StringParser();
            default:
                try {
                    return new ObjectParser(Class.forName(type));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    return null;
                }
        }
    }

    public static Parser getDefaultIntegerParser() {
        return new IntegerParser();
    }

    public static Parser getDefaultBooleanParser() {
        return new BooleanParser();
    }

    public static Parser getDefaultDoubleParser() {
        return new DoubleParser();
    }

    public static Parser getDefaultFloatParser() {
        return new FloatParser();
    }

    public static Parser getObjectParser(Class<?> clazz) {
        return new ObjectParser(clazz);
    }
}
