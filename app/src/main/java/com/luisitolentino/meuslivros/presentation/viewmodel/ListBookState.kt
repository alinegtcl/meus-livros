package com.luisitolentino.meuslivros.presentation.viewmodel

import com.luisitolentino.meuslivros.domain.model.Book

sealed class ListBookState {
    data class SearchAllSuccess(val books: List<Book>) : ListBookState()
    data class Failure(val errorMessage: String) : ListBookState()
    data object EmptyState : ListBookState()
    data object ShowLoading : ListBookState()
    data object HideLoading : ListBookState()
}
