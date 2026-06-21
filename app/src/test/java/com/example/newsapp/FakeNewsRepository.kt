package com.example.newsapp

import com.example.newsapp.data.local.NewsDao
import com.example.newsapp.data.local.NewsEntity
import com.example.newsapp.data.remote.NewsApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

interface NewsRepository {

    val news: Flow<List<NewsEntity>>

    suspend fun refresh()
}

class NewsRepositoryImpl(
    private val api: NewsApi,
    private val dao: NewsDao
) : NewsRepository {

    override val news =
        dao.getAllNews()

    override suspend fun refresh() {

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
}

class FakeNewsRepository : NewsRepository {

    private val fakeNews =

        MutableStateFlow(

            listOf(
                NewsEntity(
                    id = 1,
                    title = "Fake Title",
                    body = "Fake Body"
                )
            )
        )

    override val news =
        fakeNews.asStateFlow()

    var refreshCalled = false

    override suspend fun refresh() {

        refreshCalled = true
    }
}