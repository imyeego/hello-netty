package com.imyeego.kotlin

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.produce
import kotlin.math.PI

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
//    val job = launch {
//        try {
//            repeat(1_000) { i ->
//                println("job: I'm sleeping $i")
//                delay(500L)
//            }
//        } finally {
//            println("job: I'm running finally")
//        }
//    }
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

//    delay(1000L)
//    println("main: I'm tired of waiting!")
//    job.cancelAndJoin()
//    job1.cancelAndJoin()
//    println("main: Now I can quit.")

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
    testNullCheck()

//    var per: Person? = Person()
//    per?.name = "liuzhao"
//    println(per?.name)
//    println(s)
//    println(s)
//    runBlocking {
//        delay(2000L)
//    }
//    job.join()

    // basic of channel
//    val channel = Channel<Int>()
//    launch {
//        for (x in 1 .. 5) channel.send(x * x)
//    }
//    repeat(5) {
//        println(channel.receive())
//    }

    // iteration of channel
//    launch {
//        for (x in 1 ..5) channel.send(x * x)
//        channel.close()
//    }
//    for (y in channel) println(y)

    // pipeline
//    val numbers = produceNumbers(2)
//    val squares = square(numbers)
//    for (x in 1 .. 5) println(squares.receive())

    // generate prime array with pipeline
//    var numbers = produceNumbers(2)
//    for (x in 1 .. 10) {
//        val prime = numbers.receive()
//        println(prime)
//        numbers = filter(numbers, prime)
//    }
//    coroutineContext.cancelChildren()

    // consume a channel with multi-coroutine
//    var producer = produceNumbers(1)
//    repeat(5) {
//        launchProcessor(it, producer)
//    }
//    delay(950L)
//    producer.cancel()

    // multi-coroutine produce into a channel
//    var channel = Channel<String>()
//    launch { sendString(channel, "foo", 200L) }
//    launch { sendString(channel, "bar", 500L) }
//
//    repeat(6) {
//        println(channel.receive())
//    }
//    coroutineContext.cancelChildren()

    // channel with buffer
//    val channel = Channel<Int>(4)
//    val sender = launch {
//        repeat(10) {
//            println("sending $it ....")
//            channel.send(it)
//        }
//    }
//
//    delay(5000L)
//    sender.cancel()

    // fair in channel
//    val table = Channel<Ball>()
//    launch { player("ping", table) }
//    launch { player("pong", table) }
//    table.send(Ball(0))
//    delay(1000L)
//    coroutineContext.cancelChildren()
//
//    println("Done")



}

suspend fun delayTask(i: Int) {
    delay(500L)
    println("Hello Jay $i")
}

fun CoroutineScope.produceNumbers(start: Int) : ReceiveChannel<Int> = produce {
    var x = start
    while (true) {
        send(x++)
        delay(100L)
    }
}

fun CoroutineScope.square(numbers: ReceiveChannel<Int>) : ReceiveChannel<Int> = produce {
    for (x in numbers) send(x * x)
}

fun CoroutineScope.filter(numbers: ReceiveChannel<Int>, prime: Int) : ReceiveChannel<Int> = produce {
    for (x in numbers) {
        print("$x, ")
        if (x % prime != 0) {
            send(x)
        }
    }
}

fun CoroutineScope.launchProcessor(id: Int, channel: ReceiveChannel<Int>) = launch {
    for (message in channel) {
        println("Processor #$id received $message")
    }
}

suspend fun sendString(channel: SendChannel<String>, string: String, time: Long) {
    while (true) {
        channel.send(string)
        delay(time)
    }
}

suspend fun player(name: String, table: Channel<Ball>) {
    for (ball in table) {
        ball.hits++
        println("$name, $ball")
        delay(300L)
        table.send(ball)
    }
}

fun testGson() {
//    var json = "{\"id\":1,\"name\":null,\"age\":18,\"grade\":3,\"classTh\":2}"
    var json = "{\"id\":1,\"name\":null,\"age\":18}"
    var student = Student(1, "liuzhao", 18, 3, 2)
//    var s = Gson().toJson(student)
    var student1 = Gson().fromJson(json, Student::class.java)
    println(student1.toString())
//    var gsonBuilder = GsonBuilder()
//    gsonBuilder.serializeNulls().excludeFieldsWithoutExposeAnnotation()

//    var ob = gsonBuilder.create().fromJson(json, Student::class.java)
//    val s = gsonBuilder.create().toJson(student)
//    println(ob.toString())
//    println(s)
}

fun testNullCheck(): Unit {
    var s: String? = null

    println(s?.length)
    println(s?.length?:0)
    println(s!!.length)


    if (s != null) {
        println(s.length)
    }
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

data class Ball(var hits: Int)