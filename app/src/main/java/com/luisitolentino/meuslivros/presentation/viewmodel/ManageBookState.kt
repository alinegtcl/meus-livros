package com.luisitolentino.meuslivros.presentation.viewmodel

import com.luisitolentino.meuslivros.domain.model.Book

sealed class ManageBookState {
    data object InsertSuccess : ManageBookState()
    data object UpdateSuccess : ManageBookState()
    data object ShowLoading : ManageBookState()
    data object HideLoading : ManageBookState()
    data class GetByIdSuccess(val book: Book) : ManageBookState()
    data class Failure(val errorMessage: String) : ManageBookState()


}