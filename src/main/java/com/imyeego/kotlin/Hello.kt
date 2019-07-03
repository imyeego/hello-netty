package com.imyeego.kotlin

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

class Hello {
    internal fun to(s: String = "Jay") {
        GlobalScope.launch {
            delay(1000L)
            print("hello $s")

        }


    }
}

fun main() {
    Hello().to()
    println("Kotlin")
    Thread.sleep(2000L)
}