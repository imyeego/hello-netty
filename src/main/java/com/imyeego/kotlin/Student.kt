package com.imyeego.kotlin

import com.google.gson.annotations.Expose

data class Student(

        @Expose private var id: Int,
        @Expose private var name: String,
        private var age: Int,
        var grade: Int,
        var classTh: Int
)