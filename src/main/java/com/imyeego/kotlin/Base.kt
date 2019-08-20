package com.imyeego.kotlin

interface Base {
    fun out()
}

class BaseImpl(val x: Int) : Base {
    override fun out() {
        print(x)
    }
}

class Drived(b: Base) : Base by b

fun main() {
    var b = BaseImpl(10)
    Drived(b).out()
}