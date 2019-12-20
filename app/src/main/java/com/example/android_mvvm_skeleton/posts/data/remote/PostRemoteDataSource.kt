package com.example.android_mvvm_skeleton.posts.data.remote

import com.example.android_mvvm_skeleton.api.BaseDataSource
import javax.inject.Inject

class PostRemoteDataSource @Inject constructor(private val service: PostService) :
    BaseDataSource() {

    suspend fun fetchPosts() = getResult { service.getPosts() }

}