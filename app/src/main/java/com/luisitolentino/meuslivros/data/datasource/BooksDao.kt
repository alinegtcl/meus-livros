package com.luisitolentino.meuslivros.data.datasource

import androidx.room.Dao
import androidx.room.Insert
import com.luisitolentino.meuslivros.data.entity.BookEntity

@Dao
interface BooksDao {
    @Insert
    suspend fun insert(book: BookEntity)
}