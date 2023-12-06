package com.luisitolentino.meuslivros.data.repository

import com.luisitolentino.meuslivros.data.datasource.BooksDao
import com.luisitolentino.meuslivros.data.entity.BookEntity
import com.luisitolentino.meuslivros.domain.model.Book
import com.luisitolentino.meuslivros.domain.usecase.BookControlUseCase

class BooksRepositoryImpl(private val booksDao: BooksDao) : BookControlUseCase {

    override suspend fun insert(book: Book) {
        booksDao.insert(toBookEntity(book))
    }

    private fun toBookEntity(book: Book): BookEntity {
        return BookEntity(book.id, book.name, book.writer, book.synopsis, book.status)

    }
}