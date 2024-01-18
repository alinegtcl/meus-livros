package com.luisitolentino.meuslivros.data.repository

import MainDispatcherRule
import com.luisitolentino.meuslivros.data.datasource.BooksDao
import com.luisitolentino.meuslivros.data.entity.BookEntity
import com.luisitolentino.meuslivros.domain.model.Book
import com.luisitolentino.meuslivros.domain.usecase.BookControlUseCase
import com.luisitolentino.meuslivros.domain.utils.MBResult
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BooksRepositoryImplTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var bookRepository: BookControlUseCase
    private lateinit var dao: BooksDao

    @Before
    fun setup() {
        dao = mockk()
        bookRepository = BooksRepositoryImpl(dao)
    }

    @Test
    fun `should search a book by id with success`() {
        runTest(mainDispatcherRule.testDispatcherRule) {
            val book = Book("teste", "teste", "em progresso", "", 1)
            val expected = MBResult.Success(book)
            coEvery {
                dao.getBookById(any())
            } returns book

            val result = bookRepository.getBookById(1)

            Assert.assertEquals(expected, result)

            coVerify(exactly = 1) { dao.getBookById(any()) }
            coVerify(exactly = 0) { dao.getAllBooks() }
            coVerify(exactly = 0) { dao.insert(any()) }
            coVerify(exactly = 0) { dao.delete(any()) }
            coVerify(exactly = 0) { dao.update(any()) }
        }
    }

    @Test
    fun `should search a book by id with failure`() {
        runTest(mainDispatcherRule.testDispatcherRule) {
            val errorMessage = "Livro não encontrado"
            val expected = MBResult.Error(errorMessage)
            coEvery {
                dao.getBookById(any())
            } returns null

            val result = bookRepository.getBookById(1)

            Assert.assertEquals(expected, result)

            coVerify(exactly = 1) { dao.getBookById(any()) }
            coVerify(exactly = 0) { dao.getAllBooks() }
            coVerify(exactly = 0) { dao.insert(any()) }
            coVerify(exactly = 0) { dao.delete(any()) }
            coVerify(exactly = 0) { dao.update(any()) }

        }
    }

    @Test
    fun `should search all book with success and return books`() {
        runTest(mainDispatcherRule.testDispatcherRule) {
            val bookEntity = BookEntity(1, "teste", "teste", "", "em progresso")
            val book = Book("teste", "teste", "em progresso", "", 1)
            val expected = MBResult.Success(listOf(book))
            coEvery {
                dao.getAllBooks()
            } returns listOf(bookEntity)

            val result = bookRepository.getAllBooks()

            Assert.assertEquals(expected, result)
            coVerify(exactly = 0) { dao.getBookById(any()) }
            coVerify(exactly = 1) { dao.getAllBooks() }
            coVerify(exactly = 0) { dao.insert(any()) }
            coVerify(exactly = 0) { dao.delete(any()) }
            coVerify(exactly = 0) { dao.update(any()) }
        }
    }

    @Test
    fun `should search all book with success and return empty`() {
        runTest(mainDispatcherRule.testDispatcherRule) {
            val expected = MBResult.Success(listOf<Book>())
            coEvery {
                dao.getAllBooks()
            } returns listOf()

            val result = bookRepository.getAllBooks()

            Assert.assertEquals(expected, result)
            coVerify(exactly = 0) { dao.getBookById(any()) }
            coVerify(exactly = 1) { dao.getAllBooks() }
            coVerify(exactly = 0) { dao.insert(any()) }
            coVerify(exactly = 0) { dao.delete(any()) }
            coVerify(exactly = 0) { dao.update(any()) }
        }
    }

    @Test
    fun `should search all book with failure`() {
        runTest(mainDispatcherRule.testDispatcherRule) {
            val errorMessage = "Você não tem livros cadastrados"
            val expected = MBResult.Error(errorMessage)
            coEvery {
                dao.getAllBooks()
            } returns null

            val result = bookRepository.getAllBooks()

            Assert.assertEquals(expected, result)
            coVerify(exactly = 0) { dao.getBookById(any()) }
            coVerify(exactly = 1) { dao.getAllBooks() }
            coVerify(exactly = 0) { dao.insert(any()) }
            coVerify(exactly = 0) { dao.delete(any()) }
            coVerify(exactly = 0) { dao.update(any()) }
        }
    }

    @Test
    fun `should insert a book with success`() {
        runTest(mainDispatcherRule.testDispatcherRule) {
            val book = Book("teste", "teste", "em progresso")

            coEvery {
                dao.insert(any())
            } returns Unit

            val result = bookRepository.insert(book)

            Assert.assertEquals(Unit, result)
            coVerify(exactly = 0) { dao.getBookById(any()) }
            coVerify(exactly = 0) { dao.getAllBooks() }
            coVerify(exactly = 1) { dao.insert(any()) }
            coVerify(exactly = 0) { dao.delete(any()) }
            coVerify(exactly = 0) { dao.update(any()) }
        }
    }

    @Test
    fun `should update a book with success`() {
        runTest(mainDispatcherRule.testDispatcherRule) {
            val book = Book("teste", "teste", "em progresso")

            coEvery {
                dao.update(any())
            } returns Unit

            val result = bookRepository.update(book)

            Assert.assertEquals(Unit, result)
            coVerify(exactly = 0) { dao.getBookById(any()) }
            coVerify(exactly = 0) { dao.getAllBooks() }
            coVerify(exactly = 0) { dao.insert(any()) }
            coVerify(exactly = 0) { dao.delete(any()) }
            coVerify(exactly = 1) { dao.update(any()) }
        }
    }

    @Test
    fun `should delete a book with success`() {
        runTest(mainDispatcherRule.testDispatcherRule) {
            val book = Book("teste", "teste", "em progresso")

            coEvery {
                dao.delete(any())
            } returns Unit


            val result = bookRepository.delete(book)

            Assert.assertEquals(Unit, result)
            coVerify(exactly = 0) { dao.getBookById(any()) }
            coVerify(exactly = 0) { dao.getAllBooks() }
            coVerify(exactly = 0) { dao.insert(any()) }
            coVerify(exactly = 1) { dao.delete(any()) }
            coVerify(exactly = 0) { dao.update(any()) }
        }
    }

}