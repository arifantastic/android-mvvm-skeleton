package com.example.android_mvvm_skeleton.posts.data.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android_mvvm_skeleton.posts.data.entities.Post

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(post: Post)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(posts: List<Post>)

    @Query("SELECT * FROM table_posts")
    fun getPosts(): LiveData<List<Post>>

    @Query("SELECT * FROM table_posts")
    fun getPagedPosts(): DataSource.Factory<Int, Post>

    @Query("SELECT * FROM table_posts WHERE userId = :userId")
    fun getPagedPostsByUserId(userId: Int): DataSource.Factory<Int, Post>
}