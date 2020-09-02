package com.cellfishpool.sportsinventory.models

data class Student(
    val studentName: String,
    var phoneno: String,
    val sportsName: String,
    val status: String,
    val time: String? = null,
    val venue: String? = null
)