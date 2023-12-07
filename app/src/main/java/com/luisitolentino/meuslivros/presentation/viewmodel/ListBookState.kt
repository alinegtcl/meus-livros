package com.luisitolentino.meuslivros.presentation.viewmodel

import com.luisitolentino.meuslivros.domain.model.Book

sealed class ListBookState {
    data class SearchAllSuccess(val books: List<Book>) : ListBookState()
    data object Failure : ListBookState()
    data object EmptyState : ListBookState()
    data object ShowLoading : ListBookState()
    data object HideLoading : ListBookState()
}

