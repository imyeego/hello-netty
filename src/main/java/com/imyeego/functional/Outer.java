package com.imyeego.functional;

public class Outer {
    private Nested nested;

    public Outer(Nested nested) {
        this.nested = nested;
    }

    public Nested getNested() {
        return nested;
    }
}
