package xyz.arifz.dfatest.data.remote

import xyz.arifz.dfatest.data.local.models.PostResponse
import xyz.arifz.dfatest.data.local.models.UserResponse

class RemoteDataSourceImpl(private val apiService: ApiService) : RemoteDataSource {

    override suspend fun getUsers(): Result<List<UserResponse>> {
        return try {
            val response = apiService.getUsers()
            if (response.isSuccessful) {
                val data = response.body() ?: emptyList()
                Result.Success(data)
            } else {
                Result.Error(Exception(response.message()))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getPostsByUserId(userId: Int): Result<List<PostResponse>> {
        return try {
            val response = apiService.getPostsByUserId(userId)
            if (response.isSuccessful) {
                val data = response.body() ?: emptyList()
                Result.Success(data)
            } else {
                Result.Error(Exception(response.message()))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}
