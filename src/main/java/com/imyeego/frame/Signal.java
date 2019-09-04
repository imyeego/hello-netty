package com.imyeego.frame;

public enum Signal {
    INIT_SUCCESS(1), INIT_FAIL(2);
    int id;

    Signal(int id) {
        this.id = id;
    }
}
