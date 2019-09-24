package com.imyeego.kotlin

import com.google.gson.annotations.Expose

data class Student(

        private var id: Int,
        private var name: String,
        private var age: Int,
        var grade: Int,
        var classTh: Int
)