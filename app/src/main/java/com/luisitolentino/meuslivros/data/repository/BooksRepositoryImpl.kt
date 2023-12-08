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
        val response = booksDao.getAllBooks()
        return if (response != null)
            MBResult.Success(toDomain(response))
        else MBResult.Error("Você não tem livros cadastrados")
    }

    private fun toDomain(books: List<BookEntity>): List<Book> {
        return books.map { Book(it.name, it.writer, it.status, it.synopsis, it.id) }
    }

    override suspend fun getBookById(id: Int): MBResult<Book, String> {
        val response = booksDao.getBookById(id)
        return if (response != null) {
            MBResult.Success(response)
        } else {
            MBResult.Error("Livro não encontrado")
        }
    }
}