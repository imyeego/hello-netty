package com.imyeego.kotlin

import com.google.gson.annotations.Expose

data class Student(

        var id: Int = 0,
        var name: String = "",
        var age: Int = 0,
        var grade: Int = 0,
        var classTh: Int = 0
)