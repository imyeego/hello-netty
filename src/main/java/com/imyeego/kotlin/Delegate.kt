package com.imyeego.kotlin

import kotlin.reflect.KProperty

class Delegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>) : String{
        return "$thisRef, 这里委托了${property.name}属性"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$thisRef 的 ${property.name}属性值为 $value")
    }

}

class Example {
    var p: String by Delegate()
}

fun main() {
    val e = Example()
    println(e.p)

    e.p = "liuzhao"
    println(e.p)
}