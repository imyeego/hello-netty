package com.imyeego.kotlin

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.Callable
import kotlin.concurrent.thread
import kotlin.math.PI
import kotlin.math.truncate

class Hello {

    internal fun to(s: String = "Jay") {
        GlobalScope.launch {
            delay(1000L)
            print("hello $s")

        }


    }



    internal fun sure() : () -> Unit {
        var a = 0
        return fun() {
            a++
            println(a)
        }
    }
}

fun main() {
//    Hello().to()
//    println("Kotlin")
//    Thread.sleep(2000L)
//    var f = Hello().sure()
//    testGson()
    println(testLambda())

    thread(start = true) {
        Thread.sleep(1000)
        println("hello world!")
    }



}

fun testGson() {
    var json = "{\"id\":1,\"name\":null,\"age\":18,\"grade\":3,\"classTh\":2}"
//    var stuent = Student(1, "liuzhao", 18, 3, 2)
//    var s = Gson().toJson(stuent)
    var gsonBuilder = GsonBuilder()
    gsonBuilder.serializeNulls()

    var ob = Gson().fromJson(json, Student::class.javaObjectType)
    println(ob.toString())
}

fun testNullCheck(): Unit {
    var s: String? = null
    println(s?.length)
}

fun testLambda(): Int {

    return opration(1) { j: Int ->
        val result = j * PI
        result.toInt()
    }
}

fun opration(i: Int, add: (j: Int) -> Int) : Int{
    return i + add(i)
}