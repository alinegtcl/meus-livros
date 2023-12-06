package com.luisitolentino.meuslivros.presentation.viewmodel

sealed class BookState {
    data object Success : BookState()
    data object Failure : BookState()
    data object EmptyState : BookState()
    data object ShowLoading : BookState()
    data object HideLoading : BookState()
}
