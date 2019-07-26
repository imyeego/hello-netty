package com.imyeego.json;

public class TypeAdapterFactory {

    public static TypeAdapter getAdapterFromType(String type, String elementType) {
        switch (type) {
            case "boolean":
            case "Java.lang.Boolean":
                return new BooleanTypeAdapter();
            case "short":
            case "int":
            case "byte":
            case "java.lang.Integer":
            case "java.lang.Short":
            case "java.lang.Byte":
            case "java.math.BigInteger":
                return new IntegerTypeAdapter();
            case "long":
            case "java.lang.Long":
                return new LongTypeAdapter();
            case "float":
            case "java.lang.Float":
                return new FloatTypeAdapter();
            case "double":
            case "java.lang.Double":
            case "java.math.BigDecimal":
                return new DoubleTypeAdapter();
            case "java.lang.String":
                return new StringTypeAdapter();
            case "java.util.List":
                try {
                    return new ListTypeAdapter(Class.forName(elementType));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            default:
                try {
                    return new ObjectTypeAdapter(Class.forName(type));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    return null;
                }
        }
    }

    public static TypeAdapter getDefaultIntegerParser() {
        return new IntegerTypeAdapter();
    }
    public static TypeAdapter getDefaultLongParser() {
        return new LongTypeAdapter();
    }

    public static TypeAdapter getDefaultBooleanParser() {
        return new BooleanTypeAdapter();
    }

    public static TypeAdapter getDefaultDoubleParser() {
        return new DoubleTypeAdapter();
    }

    public static TypeAdapter getDefaultFloatParser() {
        return new FloatTypeAdapter();
    }

    public static TypeAdapter getObjectParser(Class<?> clazz) {
        return new ObjectTypeAdapter(clazz);
    }
}
