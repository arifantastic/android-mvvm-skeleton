package xyz.arifz.dfatest.data.local

import xyz.arifz.dfatest.data.local.models.PostEntity
import xyz.arifz.dfatest.data.local.models.UserEntity

interface LocalDataSource {

    suspend fun saveUsers(users: List<UserEntity>)

    suspend fun savePosts(posts: List<PostEntity>)

    suspend fun getUsers(): List<UserEntity>

    suspend fun getPostsByUserId(userId: Int): List<PostEntity>
}
