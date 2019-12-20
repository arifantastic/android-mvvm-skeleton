package com.example.android_mvvm_skeleton.posts.data.remote

import com.example.android_mvvm_skeleton.posts.data.entities.Post
import retrofit2.Response
import retrofit2.http.GET

interface PostService {

    @GET("/posts")
    suspend fun getPosts(): Response<List<Post>>

}