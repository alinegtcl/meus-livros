package com.luisitolentino.meuslivros.data.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.luisitolentino.meuslivros.data.entity.BookEntity

@Dao
interface BooksDao {
    @Insert
    suspend fun insert(book: BookEntity)

    @Query("SELECT * FROM bookentity ORDER BY name")
    suspend fun getAllBooks(): List<BookEntity>
}