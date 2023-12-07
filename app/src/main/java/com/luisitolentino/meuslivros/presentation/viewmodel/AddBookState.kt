package com.luisitolentino.meuslivros.presentation.viewmodel

sealed class AddBookState {
    data object InsertSuccess : AddBookState()
    data object ShowLoading : AddBookState()
    data object HideLoading : AddBookState()
}