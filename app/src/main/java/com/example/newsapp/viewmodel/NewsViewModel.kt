package com.example.newsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.local.NewsEntity
import com.example.newsapp.data.local.NewsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: com.example.newsapp.data.repository.NewsRepository
) : ViewModel() {

    fun getNewsById(
        id: Int
    ): Flow<NewsEntity?> {

        return repository.getNewsById(id)
    }
    private val _uiState =
        MutableStateFlow(
            NewsUiState()
        )

    val uiState =
        _uiState.asStateFlow()

    init {

        observeNews()

        refresh()
    }

    private fun observeNews() {

        viewModelScope.launch {

            repository.news.collect { newsList ->

                _uiState.update { state ->

                    state.copy(news = newsList)
                }
            }
        }
    }

    fun refresh() {

        viewModelScope.launch {

            _uiState.update {

                it.copy(
                    loading = true
                )
            }

            try {

                repository.refresh()

            } finally {

                _uiState.update {

                    it.copy(
                        loading = false
                    )
                }
            }
        }
    }
}