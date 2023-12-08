package com.luisitolentino.meuslivros.domain.usecase

import com.luisitolentino.meuslivros.domain.model.Book
import com.luisitolentino.meuslivros.domain.utils.MBResult

interface BookControlUseCase {
    suspend fun insert(book: Book)

    suspend fun update(book: Book)

    suspend fun getAllBooks(): MBResult<List<Book>, String>

    suspend fun getBookById(id: Int): MBResult<Book, String>
}