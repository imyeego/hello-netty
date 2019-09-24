package com.imyeego.kotlin

import kotlinx.coroutines.*

/**
 *  上下文与调度器
 *  url : https://www.kotlincn.net/docs/reference/coroutines/coroutine-context-and-dispatchers.html
 */
fun main()  {

//    launch {
//        println("main runBlocking: I'm working in thread ${Thread.currentThread().name}")
//        delay(1000L)
//        println("main runBlocking: After I'm working in thread ${Thread.currentThread().name}")
//
//    }
//
//    launch(Dispatchers.Unconfined) {
//        println("Unconfined: I'm working in thread ${Thread.currentThread().name}")
//        delay(1000L)
//        println("Unconfined: After I'm working in thread ${Thread.currentThread().name}")
//
//    }

//    launch(Dispatchers.Default) {
//        println("Default: I'm working in thread ${Thread.currentThread().name}")
//    }
//
//    launch(newSingleThreadContext("MyThread")) {
//        println("MyThread: I'm working in thread ${Thread.currentThread().name}")
//    }

    // debug coroutine
//    val a = async {
//        log("I'm computing a piece of the answer")
//        6
//    }
//
//    val b = async {
//        log("I'm computing another a piece of the answer")
//        7
//    }
//
//    log("The answer is ${a.await() * b.await()}")

    // jump between different coroutine
    newSingleThreadContext("Ctx1").use { ctx1 ->
        newSingleThreadContext("Ctx2").use { ctx2 ->
            runBlocking(ctx1) {
                log("started in ctx1")
                withContext(ctx2) {
                    log("working in ctx2")
                }
                log("back to ctx1")
            }
        }
    }

    println("Coroutine Context!")
}

fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")