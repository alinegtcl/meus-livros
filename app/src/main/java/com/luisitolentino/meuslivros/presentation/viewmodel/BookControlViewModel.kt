package com.luisitolentino.meuslivros.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luisitolentino.meuslivros.domain.model.Book
import com.luisitolentino.meuslivros.domain.usecase.BookControlUseCase
import kotlinx.coroutines.launch

class BookControlViewModel(private val useCase: BookControlUseCase) : ViewModel() {
    private val _state = MutableLiveData<BookState>()
    val state: LiveData<BookState>
        get() = _state

    fun insert(book: Book) {
        viewModelScope.launch {
            useCase.insert(book)
        }
    }
}