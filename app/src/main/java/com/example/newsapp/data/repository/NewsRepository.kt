package com.example.newsapp.data.repository

import androidx.room.Query
import com.example.newsapp.data.local.NewsDao
import com.example.newsapp.data.local.NewsEntity
import com.example.newsapp.data.remote.NewsApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val api: NewsApi,
    private val dao: NewsDao
) {

    val news =
        dao.getAllNews()

    suspend fun refresh() {

        val remotePosts =
            api.getPosts()

        dao.insertAll(

            remotePosts.map {

                NewsEntity(
                    id = it.id,
                    title = it.title,
                    body = it.body
                )
            }
        )
    }

    fun getNewsById(
        id: Int
    ): Flow<NewsEntity?> {
        return dao.getNewsById(id)
    }

}

