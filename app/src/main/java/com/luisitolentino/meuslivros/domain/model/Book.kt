package com.luisitolentino.meuslivros.domain.model

data class Book(
    val name: String,
    val writer: String,
    val status: String,
    val synopsis: String? = "",
    var id: Int = 0,
)
