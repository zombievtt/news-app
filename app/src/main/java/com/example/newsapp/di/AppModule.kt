package com.example.newsapp.di

import android.content.Context
import androidx.room.Room
import com.example.newsapp.data.local.NewsDao
import com.example.newsapp.data.local.NewsDatabase
import com.example.newsapp.data.remote.NewsApi
import com.example.newsapp.data.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {

        return Retrofit.Builder()
            .baseUrl(
                "https://jsonplaceholder.typicode.com/"
            )
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): NewsDatabase {

        return Room.databaseBuilder(
            context,
            NewsDatabase::class.java,
            "news_db"
        ).build()
    }

    @Provides
    fun provideNewsDao(
        database: NewsDatabase
    ): NewsDao {

        return database.newsDao()
    }

    @Provides
    @Singleton
    fun provideRepository(
        api: NewsApi,
        dao: NewsDao
    ): NewsRepository {

        return NewsRepository(
            api,
            dao
        )
    }
}