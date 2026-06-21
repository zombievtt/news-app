package com.example.newsapp

import com.example.newsapp.data.local.NewsDao
import com.example.newsapp.data.remote.NewsApi
import com.example.newsapp.data.remote.PostDto
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class NewsRepositoryTest {

    @get:Rule
    val dispatcherRule =
        MainDispatcherRule()

    @Test
    fun refresh_should_insert_news()
            = runTest {

        val dao = mockk<NewsDao>()
        val api = mockk<NewsApi>()

        coEvery {
            dao.getAllNews()
        } returns flowOf(emptyList())

        coEvery {
            api.getPosts()
        } returns listOf(
            PostDto(
                1,
                "Hello",
                "World"
            )
        )

        coEvery {
            dao.insertAll(any())
        } just Runs

        val repository =
            NewsRepositoryImpl(
                api,
                dao
            )

        repository.refresh()

        coVerify {
            dao.insertAll(
                match {
                    it.size == 1 &&
                            it[0].title == "Hello"
                }
            )
        }
    }
}