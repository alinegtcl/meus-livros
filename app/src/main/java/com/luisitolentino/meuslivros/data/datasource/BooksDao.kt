package com.luisitolentino.meuslivros.data.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.luisitolentino.meuslivros.data.entity.BookEntity
import com.luisitolentino.meuslivros.domain.model.Book

@Dao
interface BooksDao {
    @Insert
    suspend fun insert(book: BookEntity)

    @Update
    suspend fun update(book: BookEntity)

    @Query("SELECT * FROM bookentity ORDER BY name")
    suspend fun getAllBooks(): List<BookEntity>?

    @Query("SELECT * FROM bookentity WHERE id=:id")
    suspend fun getBookById(id: Int): Book?
}