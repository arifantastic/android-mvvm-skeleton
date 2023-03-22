package xyz.arifz.dfatest.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import xyz.arifz.dfatest.data.local.models.PostResponse
import xyz.arifz.dfatest.data.local.models.UserResponse

interface ApiService {

    @GET("/users")
    suspend fun getUsers(): Response<List<UserResponse>>

    @GET("/posts")
    suspend fun getPostsByUserId(@Query("userId") userId: Int): Response<List<PostResponse>>
}
