package com.imyeego.kotlin

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis


/**
 * 协程：组合挂起函数
 * {@link https://www.kotlincn.net/docs/reference/coroutines/composing-suspending-functions.html}
 */

fun main() = runBlocking {
    val time = measureTimeMillis {
        // default
//        val one = one()
//        val two = two()
//        println("The answer is ${one + two}")

        // use async
//        val one = async { one() }
//        val two = async { two() }
//        println("The answer is ${one.await() + two.await()}")

        // use asyns-style function
//        val one = oneAsync()
//        val two = twoAsync()
//
//        runBlocking {
//            println("The answer is ${one.await() + two.await()}")
//        }

        // structure concurrent
//        println("The answer is ${concurrentSum()}")

        try {
            failedConcurrentSum()
        } catch (e: ArithmeticException) {
            println("Computation failed with ArithmeticException")
        }


    }
    println("Completed in $time ms")
}

suspend fun one() : Int {
    delay(1000L)
    return 1
}

suspend fun two() : Int {
    delay(1000L)
    return 2
}

fun oneAsync() = GlobalScope.async {
    one()
}

fun twoAsync() = GlobalScope.async {
    two()
}

suspend fun concurrentSum() : Int = coroutineScope {
    val one = async { one() }
    val two = async { two() }
    one.await() + two.await()
}

suspend fun failedConcurrentSum() : Int = coroutineScope {
    val one = async {
        try {
            delay(Long.MAX_VALUE)
            1
        } finally {
            println("First child was cancelled")
        }
    }

    val two = async<Int> {
        println("Second child throws an exception")
        throw ArithmeticException()
    }

    one.await() + two.await()
}
