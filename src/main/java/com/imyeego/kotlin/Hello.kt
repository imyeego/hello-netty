package com.imyeego.kotlin

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.*
import java.util.concurrent.Callable
import java.util.concurrent.CompletableFuture
import kotlin.concurrent.thread
import kotlin.math.PI
import kotlin.math.truncate

object Hello {

    internal fun to(s: String = "Jay") : Job{
        return GlobalScope.launch {
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

val s: String by lazy {
    println("compute!")
    "hello"
}

fun main() = runBlocking {
//    repeat(100_000) { i ->
//        launch {
//            delayTask(i)
//        }
//    }
    val job = launch {
        try {
            repeat(1_000) { i ->
                println("job: I'm sleeping $i")
                delay(500L)
            }
        } finally {
            println("job: I'm running finally")
        }
    }
//    val start = System.currentTimeMillis()
//    val job1 = launch(Dispatchers.Default) {
//        var next = start
//        var i = 0
//        while (isActive) {
//            if (System.currentTimeMillis() > next) {
//                println("job: I'm sleeping ${i++}")
//                next += 500L
//            }
//        }
//    }

    delay(1000L)
    println("main: I'm tired of waiting!")
    job.cancelAndJoin()
//    job1.cancelAndJoin()
    println("main: Now I can quit.")

//    job.join()
//    coroutineScope {
//        launch {
//            delay(2000L)
//            println("Hello Liu")
//        }
//        delay(500L)
//        println("Hello Task")
//    }
//    println("Complete!")

//    Thread.sleep(2000L)
//    var f = Hello().sure()
//    testGson()
//    println(testLambda())
//
//    thread(start = true) {
//        Thread.sleep(1000)
//        println("hello world!")
//    }
//    testNullCheck()

//    var per: Person? = Person()
//    per?.name = "liuzhao"
//    println(per?.name)
//    println(s)
//    println(s)
//    runBlocking {
//        delay(2000L)
//    }
//    job.join()

}

suspend fun delayTask(i: Int) {
    delay(500L)
    println("Hello Jay $i")
}

fun testGson() {
    var json = "{\"id\":1,\"name\":null,\"age\":18,\"grade\":3,\"classTh\":2}"
    var student = Student(1, "liuzhao", 18, 3, 2)
//    var s = Gson().toJson(stuent)
    var gsonBuilder = GsonBuilder()
    gsonBuilder.serializeNulls().excludeFieldsWithoutExposeAnnotation()

    var ob = gsonBuilder.create().fromJson(json, Student::class.java)
    val s = gsonBuilder.create().toJson(student)
    println(ob.toString())
    println(s)
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