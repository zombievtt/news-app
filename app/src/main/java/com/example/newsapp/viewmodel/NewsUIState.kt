package com.example.newsapp.viewmodel

import com.example.newsapp.data.local.NewsEntity

data class NewsUiState(

    val loading: Boolean = false,

    val news: List<NewsEntity> =
        emptyList(),

    val error: String? = null
)