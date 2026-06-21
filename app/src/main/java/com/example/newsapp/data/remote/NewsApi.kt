package com.example.newsapp.data.remote

import retrofit2.http.GET



interface NewsApi {

    @GET("posts")
    suspend fun getPosts(): List<PostDto>
}