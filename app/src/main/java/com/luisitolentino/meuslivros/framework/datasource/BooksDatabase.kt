package com.luisitolentino.meuslivros.framework.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.luisitolentino.meuslivros.data.datasource.BooksDao
import com.luisitolentino.meuslivros.data.entity.BookEntity

@Database(entities = [BookEntity::class], version = 1)
abstract class BooksDatabase : RoomDatabase() {
    abstract fun booksDao(): BooksDao

    companion object {
        const val MY_BOOKS_DATABASE_NAME = "mybooks.db"
//        @Volatile
//        private var INSTANCE: BooksDatabase? = null
//
//        fun getDatabase(context: Context) : BooksDatabase {
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    BooksDatabase::class.java,
//                    "mybooks.db"
//                ).build()
//                INSTANCE = instance
//                instance
//            }
//        }
    }
}