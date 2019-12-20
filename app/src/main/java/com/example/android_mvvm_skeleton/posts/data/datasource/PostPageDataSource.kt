package com.example.android_mvvm_skeleton.posts.data.datasource

import androidx.paging.PageKeyedDataSource
import com.example.android_mvvm_skeleton.data.Result
import com.example.android_mvvm_skeleton.posts.data.dao.PostDao
import com.example.android_mvvm_skeleton.posts.data.entities.Post
import com.example.android_mvvm_skeleton.posts.data.remote.PostRemoteDataSource
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class PostPageDataSource @Inject constructor(
    private val remoteDataSource: PostRemoteDataSource,
    private val dao: PostDao,
    private val scope: CoroutineScope,
    private val userId: Int? = null
) : PageKeyedDataSource<Int, Post>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Post>) {
        fetchPostData {
            callback.onResult(it, null, 2)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Post>) {
        val page = params.key
        fetchPostData {
            callback.onResult(it, page + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Post>) {
        val page = params.key
        fetchPostData {
            callback.onResult(it, page - 1)
        }
    }

    private fun fetchPostData(callback: (List<Post>) -> Unit) {
        scope.launch(getJobErrorHandler()) {
            val response = remoteDataSource.fetchPosts()
            if (response.status == Result.Status.SUCCESS) {
                val results = response.data
                if (!results.isNullOrEmpty()) {
                    dao.insertAll(results)
                    callback(results)
                } else {
                    postError("Empty data")
                }
            } else if (response.status == Result.Status.ERROR) {
                postError(response.message!!)
            }
        }
    }

    private fun getJobErrorHandler() = CoroutineExceptionHandler { _, e ->
        postError(e.message ?: e.toString())
    }

    private fun postError(message: String) {
        Timber.e("An error happened: $message")

        // TODO network error handling
        //networkState.postValue(NetworkState.FAILED)
    }
}