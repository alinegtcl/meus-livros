package com.luisitolentino.meuslivros.domain.model

data class Book(
    var id: Int = 0,
    val name: String,
    val writer: String,
    val synopsis: String? = "",
    val status: String
)
