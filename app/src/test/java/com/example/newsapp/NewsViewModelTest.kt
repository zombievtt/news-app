package com.example.newsapp



import com.example.newsapp.data.local.NewsEntity
import com.example.newsapp.viewmodel.NewsViewModel
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.Rule
import com.example.newsapp.data.repository.NewsRepository

@OptIn(ExperimentalCoroutinesApi::class)
class NewsViewModelTest {

    @get:Rule
    val dispatcherRule =
        MainDispatcherRule()

    @Test
    fun observe_news_should_update_ui_state()
            = runTest {

        val repository =
            mockk<NewsRepository>()

        every {
            repository.news
        } returns MutableStateFlow(
            listOf(
                NewsEntity(
                    id = 1,
                    title = "Fake Title",
                    body = "Fake Body"
                )
            )
        )

        coEvery {
            repository.refresh()
        } just Runs

        val viewModel =
            NewsViewModel(repository)

        advanceUntilIdle()

        assertEquals(
            1,
            viewModel.uiState.value.news.size
        )

        assertEquals(
            "Fake Title",
            viewModel.uiState.value.news[0].title
        )
    }
}