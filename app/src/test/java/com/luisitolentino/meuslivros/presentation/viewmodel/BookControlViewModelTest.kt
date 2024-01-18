package com.luisitolentino.meuslivros.presentation.viewmodel

import MainDispatcherRule
import com.luisitolentino.meuslivros.domain.model.Book
import com.luisitolentino.meuslivros.domain.usecase.BookControlUseCase
import com.luisitolentino.meuslivros.domain.utils.MBResult
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BookControlViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var bookControlViewModel: BookControlViewModel
    private lateinit var useCase: BookControlUseCase

    @Before
    fun setup() {
        useCase = mockk()
        bookControlViewModel = BookControlViewModel(useCase)
    }

    @Test
    fun `should search a book by id with success`() {
        runTest(mainDispatcherRule.testDispatcherRule) {
            val book = Book("teste", "teste", "em progresso", "", 1)

            coEvery {
                useCase.getBookById(any())
            } returns MBResult.Success(book)

            assertEquals(bookControlViewModel.stateManagement.value, ManageBookState.HideLoading)

            bookControlViewModel.getBookById(1)

            assertEquals(
                bookControlViewModel.stateManagement.value,
                ManageBookState.GetByIdSuccess(book)
            )
            coVerify(exactly = 1) { useCase.getBookById(any()) }
        }
    }

    @Test
    fun `should search a book by id with failure`() {
        runTest(mainDispatcherRule.testDispatcherRule) {
            val errorMessage = "Livro não encontrado"
            coEvery {
                useCase.getBookById(any())
            } returns MBResult.Error(errorMessage)

            assertEquals(bookControlViewModel.stateManagement.value, ManageBookState.HideLoading)

            bookControlViewModel.getBookById(1)

            assertEquals(
                bookControlViewModel.stateManagement.value,
                ManageBookState.Failure(errorMessage)
            )
            coVerify(exactly = 1) { useCase.getBookById(any()) }
        }
    }

    @Test
    fun `should search all book with success and return books`() {
        runTest(mainDispatcherRule.testDispatcherRule) {
            val book = Book("teste", "teste", "em progresso", "", 1)

            coEvery {
                useCase.getAllBooks()
            } returns MBResult.Success(listOf(book))

            assertEquals(bookControlViewModel.stateList.value, ListBookState.HideLoading)

            bookControlViewModel.getAllBooks()

            assertEquals(
                bookControlViewModel.stateList.value,
                ListBookState.SearchAllSuccess(listOf(book))
            )
            coVerify(exactly = 1) { useCase.getAllBooks() }
        }
    }

    @Test
    fun `should search all book with success and return empty`() {
        runTest(mainDispatcherRule.testDispatcherRule) {
            coEvery {
                useCase.getAllBooks()
            } returns MBResult.Success(listOf())

            assertEquals(bookControlViewModel.stateList.value, ListBookState.HideLoading)

            bookControlViewModel.getAllBooks()

            assertEquals(bookControlViewModel.stateList.value, ListBookState.EmptyState)
            coVerify(exactly = 1) { useCase.getAllBooks() }
        }
    }

    @Test
    fun `should search all book with failure`() {
        runTest(mainDispatcherRule.testDispatcherRule) {
            val errorMessage = "Você não tem livros cadastrados"
            coEvery {
                useCase.getAllBooks()
            } returns MBResult.Error(errorMessage)

            assertEquals(bookControlViewModel.stateList.value, ListBookState.HideLoading)

            bookControlViewModel.getAllBooks()

            assertEquals(bookControlViewModel.stateList.value, ListBookState.Failure(errorMessage))
            coVerify(exactly = 1) { useCase.getAllBooks() }
        }
    }

    @Test
    fun `should insert a book with success`() {
        runTest(mainDispatcherRule.testDispatcherRule) {
            val book = Book("teste", "teste", "em progresso")

            coEvery {
                useCase.insert(any())
            } returns Unit

            assertEquals(bookControlViewModel.stateManagement.value, ManageBookState.HideLoading)

            bookControlViewModel.insert(book)

            assertEquals(bookControlViewModel.stateManagement.value, ManageBookState.InsertSuccess)
            coVerify(exactly = 1) { useCase.insert(any()) }
        }
    }

    @Test
    fun `should update a book with success`() {
        runTest(mainDispatcherRule.testDispatcherRule) {
            val book = Book("teste", "teste", "em progresso")

            coEvery {
                useCase.update(any())
            } returns Unit

            assertEquals(bookControlViewModel.stateManagement.value, ManageBookState.HideLoading)

            bookControlViewModel.update(book)

            assertEquals(bookControlViewModel.stateManagement.value, ManageBookState.UpdateSuccess)
            coVerify(exactly = 1) { useCase.update(any()) }
        }
    }

    @Test
    fun `should delete a book with success`() {
        runTest(mainDispatcherRule.testDispatcherRule) {
            val book = Book("teste", "teste", "em progresso")

            coEvery {
                useCase.delete(any())
            } returns Unit

            assertEquals(bookControlViewModel.stateManagement.value, ManageBookState.HideLoading)

            bookControlViewModel.delete(book)

            assertEquals(bookControlViewModel.stateManagement.value, ManageBookState.DeleteSuccess)
            coVerify(exactly = 1) { useCase.delete(any()) }
        }
    }

}