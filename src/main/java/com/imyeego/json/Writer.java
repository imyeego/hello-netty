package com.imyeego.json;

public class Writer {
    private StringBuilder stringBuilder;
    private long pos;

    public Writer() {
        stringBuilder = new StringBuilder();
        pos = -1;
    }

    public Writer writeName(String name) {
        stringBuilder.append('\"').append(name).append('\"').append(':');
        return this;
    }

    public void startObject() {
        stringBuilder.append('{');

    }

    public void endObject() {
        toPrevious();
        stringBuilder.append('}');

    }

    public void beginArray() {
        stringBuilder.append('[');

    }

    public void nextArray() {
        stringBuilder.append(',');
    }

    public void endArray() {
        toPrevious();
        stringBuilder.append(']');

    }

    public Writer writeBoolean(Boolean value) {
        stringBuilder.append(value).append(',');
        return this;
    }

    public Writer writeDouble(Double value) {
        stringBuilder.append(value).append(',');
        return this;
    }

    public Writer writeFloat(Float value) {
        stringBuilder.append(value).append(',');
        return this;
    }

    public Writer writeInteger(Integer value) {
        stringBuilder.append(value).append(',');
        return this;
    }

    public Writer writeLong(Long value) {
        stringBuilder.append(value).append(',');
        return this;
    }

    public Writer writeString(String value) {
        stringBuilder.append('\"').append(value).append('\"').append(',');
        return this;
    }

    public Writer toPrevious() {
        if (stringBuilder.charAt(stringBuilder.length() - 1) == ',')
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return this;
    }

    public String getString() {
        return stringBuilder.toString();
    }
}
