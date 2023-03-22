package xyz.arifz.dfatest.data.remote

import xyz.arifz.dfatest.data.local.models.PostResponse
import xyz.arifz.dfatest.data.local.models.UserResponse

interface RemoteDataSource {

    suspend fun getUsers(): Result<List<UserResponse>>

    suspend fun getPostsByUserId(userId: Int): Result<List<PostResponse>>
}
