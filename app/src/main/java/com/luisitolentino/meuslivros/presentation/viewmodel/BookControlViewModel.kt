package com.luisitolentino.meuslivros.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luisitolentino.meuslivros.domain.model.Book
import com.luisitolentino.meuslivros.domain.usecase.BookControlUseCase
import com.luisitolentino.meuslivros.domain.utils.flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BookControlViewModel(private val useCase: BookControlUseCase) : ViewModel() {

    private val _stateList = MutableStateFlow<ListBookState>(ListBookState.HideLoading)
    val stateList = _stateList.asStateFlow()

    private val _stateManagement = MutableStateFlow<AddBookState>(AddBookState.HideLoading)
    val stateManagement = _stateManagement.asStateFlow()
    fun insert(book: Book) {
        viewModelScope.launch {
            _stateManagement.value = AddBookState.ShowLoading
            useCase.insert(book)
            _stateManagement.value = AddBookState.HideLoading
            _stateManagement.value = AddBookState.InsertSuccess
        }
    }

    fun getAllBooks() {
        viewModelScope.launch {
            _stateList.value = ListBookState.ShowLoading
            val response = useCase.getAllBooks()
            _stateList.value = ListBookState.HideLoading
            response.flow(
                { books ->
                    if (books.size > 1)
                        _stateList.value = ListBookState.SearchAllSuccess(books)
                    else
                        _stateList.value = ListBookState.EmptyState
                }, {
                    _stateList.value = ListBookState.Failure
                }
            )
        }
    }
}
