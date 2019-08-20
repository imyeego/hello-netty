package com.imyeego.kotlin

class Person {
    var name: String = ""
        get() = field.toUpperCase()
        set(value) {
            field = value.toUpperCase()
        }


    var id: Int = 0
}