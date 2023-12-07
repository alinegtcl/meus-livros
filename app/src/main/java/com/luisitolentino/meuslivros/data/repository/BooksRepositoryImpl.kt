package com.luisitolentino.meuslivros.data.repository

import com.luisitolentino.meuslivros.data.datasource.BooksDao
import com.luisitolentino.meuslivros.data.entity.BookEntity
import com.luisitolentino.meuslivros.domain.model.Book
import com.luisitolentino.meuslivros.domain.usecase.BookControlUseCase
import com.luisitolentino.meuslivros.domain.utils.MBResult

class BooksRepositoryImpl(private val booksDao: BooksDao) : BookControlUseCase {

    override suspend fun insert(book: Book) {
        booksDao.insert(toBookEntity(book))
    }

    private fun toBookEntity(book: Book): BookEntity {
        return BookEntity(book.id, book.name, book.writer, book.synopsis, book.status)
    }

    override suspend fun getAllBooks(): MBResult<List<Book>, String> {
        return MBResult.Success(toDomain(booksDao.getAllBooks()))
    }

    private fun toDomain(books: List<BookEntity>): List<Book> {
        return books.map { Book(it.id, it.name, it.writer, it.synopsis, it.status) }
    }
}