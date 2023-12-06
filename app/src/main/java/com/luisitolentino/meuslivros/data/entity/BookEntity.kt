package com.luisitolentino.meuslivros.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BookEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val writer: String,
    val synopsis: String?,
    val status: String
)
