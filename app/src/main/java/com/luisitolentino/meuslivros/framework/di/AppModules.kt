package com.luisitolentino.meuslivros.framework.di

import androidx.room.Room
import com.luisitolentino.meuslivros.data.repository.BooksRepositoryImpl
import com.luisitolentino.meuslivros.domain.usecase.BookControlUseCase
import com.luisitolentino.meuslivros.framework.datasource.BooksDatabase
import com.luisitolentino.meuslivros.presentation.viewmodel.BookControlViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {
    single {
        Room
            .databaseBuilder(
                androidContext(),
                BooksDatabase::class.java,
                BooksDatabase.MY_BOOKS_DATABASE_NAME
            )
            .build()
    }
    single { get<BooksDatabase>().booksDao() }
    single<BookControlUseCase> { BooksRepositoryImpl(get()) }
    viewModel { BookControlViewModel(get()) }
}