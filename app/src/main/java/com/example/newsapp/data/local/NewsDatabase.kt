package com.example.newsapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [NewsEntity::class],
    version = 1,
    exportSchema = false
)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao

//    companion object {
//
//        @Volatile
//        private var INSTANCE:
//                NewsDatabase? = null
//
//        fun getDatabase(
//            context: Context
//        ): NewsDatabase {
//
//            return INSTANCE
//                ?: synchronized(this) {
//
//                    Room.databaseBuilder(
//                        context,
//                        NewsDatabase::class.java,
//                        "news_db"
//                    )
//                        .build()
//                        .also {
//
//                            INSTANCE = it
//                        }
//                }
//        }
//    }
}