package com.imyeego.functional;

public class Nested {

    private Inner inner;

    public Nested(Inner inner) {
        this.inner = inner;
    }

    public Inner getInner() {
        return inner;
    }
}
