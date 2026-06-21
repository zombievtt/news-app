package com.example.newsapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class NewsEntity(

    @PrimaryKey
    val id: Int,

    val title: String,

    val body: String
)

data class NewsUiState(

    val loading: Boolean = false,

    val news: List<NewsEntity> = emptyList(),

    val error: String? = null
)