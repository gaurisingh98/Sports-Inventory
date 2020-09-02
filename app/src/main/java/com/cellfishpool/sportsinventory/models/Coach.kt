package com.cellfishpool.sportsinventory.models

data class Coach(
    val studentName: String,
    val sportsName: String,
    val status: String,
    val time: String? = null,
    val venue: String? = null
)