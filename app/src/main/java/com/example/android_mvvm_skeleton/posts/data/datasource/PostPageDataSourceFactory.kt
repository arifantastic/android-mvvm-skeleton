package com.example.android_mvvm_skeleton.posts.data.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.example.android_mvvm_skeleton.posts.data.dao.PostDao
import com.example.android_mvvm_skeleton.posts.data.entities.Post
import com.example.android_mvvm_skeleton.posts.data.remote.PostRemoteDataSource
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class PostPageDataSourceFactory @Inject constructor(
    private val remoteDataSource: PostRemoteDataSource,
    private val dao: PostDao,
    private val scope: CoroutineScope
) : DataSource.Factory<Int, Post>() {

    private val liveData = MutableLiveData<PostPageDataSource>()

    override fun create(): DataSource<Int, Post> {
        val source = PostPageDataSource(remoteDataSource, dao, scope)
        liveData.postValue(source)
        return source
    }

    companion object {
        private const val PAGE_SIZE = 100

        fun pagedListConfig() = PagedList.Config.Builder()
            .setInitialLoadSizeHint(PAGE_SIZE)
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(true)
            .build()
    }
}
