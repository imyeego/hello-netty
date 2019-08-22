package com.imyeego.frame

object Test {

    @JvmStatic
    fun main(args: Array<String>) {
        //        System.out.println(Lambda.ss);
        val singleTon = SingleTon.getInstance()
        println("count1=" + SingleTon.count1)
        println("count2=" + SingleTon.count2)
    }
}
