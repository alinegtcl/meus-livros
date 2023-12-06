package com.luisitolentino.meuslivros.domain.usecase

import com.luisitolentino.meuslivros.domain.model.Book

interface BookControlUseCase {
    suspend fun insert(book: Book)
}