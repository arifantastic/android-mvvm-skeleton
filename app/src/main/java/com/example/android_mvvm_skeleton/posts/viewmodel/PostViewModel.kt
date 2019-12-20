package com.example.android_mvvm_skeleton.posts.viewmodel

import androidx.lifecycle.ViewModel
import com.example.android_mvvm_skeleton.di.CoroutineScropeIO
import com.example.android_mvvm_skeleton.posts.data.repository.PostRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import javax.inject.Inject

class PostViewModel @Inject constructor(
    private val respository: PostRepository,
    @CoroutineScropeIO private val ioCoroutineScope: CoroutineScope
) : ViewModel() {
    var connectivityAvailable: Boolean = false

    val posts by lazy {
        respository.observePosts(connectivityAvailable, ioCoroutineScope)
    }

    /**
     * Cancel all Coroutines when the ViewModel is cleared.
     */
    override fun onCleared() {
        super.onCleared()
        ioCoroutineScope.cancel()
    }
}