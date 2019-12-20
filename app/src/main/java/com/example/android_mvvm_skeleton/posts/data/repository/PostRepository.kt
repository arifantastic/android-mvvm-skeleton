package com.example.android_mvvm_skeleton.posts.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.android_mvvm_skeleton.posts.data.dao.PostDao
import com.example.android_mvvm_skeleton.posts.data.datasource.PostPageDataSourceFactory
import com.example.android_mvvm_skeleton.posts.data.entities.Post
import com.example.android_mvvm_skeleton.posts.data.remote.PostRemoteDataSource
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostRepository @Inject constructor(
    private val dao: PostDao,
    private val remoteDataSource: PostRemoteDataSource
) {
    fun observePosts(
        connectivityAvailable: Boolean,
        scope: CoroutineScope
    ): LiveData<PagedList<Post>> {
        return if (connectivityAvailable) observeRemotePosts(scope)
        else observeLocalPosts()
    }

    private fun observeLocalPosts(): LiveData<PagedList<Post>> {
        val dataSourceFactory = dao.getPagedPosts()
        return LivePagedListBuilder(
            dataSourceFactory,
            PostPageDataSourceFactory.pagedListConfig()
        ).build()
    }

    private fun observeRemotePosts(scope: CoroutineScope): LiveData<PagedList<Post>> {
        val dataSourceFactory = PostPageDataSourceFactory(remoteDataSource, dao, scope)
        return LivePagedListBuilder(
            dataSourceFactory,
            PostPageDataSourceFactory.pagedListConfig()
        ).build()
    }
}