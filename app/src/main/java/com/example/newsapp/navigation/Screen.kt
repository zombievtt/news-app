package com.example.newsapp.navigation

import com.example.newsapp.data.local.NewsEntity

sealed class Screen(
    val route: String
) {

    object NewsList :
        Screen("news_list")

    object NewsDetail :
        Screen("news_detail/{newsId}") {

        fun createRoute(
            newsId: Int
        ) = "news_detail/$newsId"
    }
}