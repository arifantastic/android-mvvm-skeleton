package xyz.arifz.dfatest.domain.repository

import xyz.arifz.dfatest.presentation.utils.UserEntity

interface UserRepository {

    suspend fun getUsers(): Result<List<UserEntity>>

    suspend fun getPostsByUserId(userId: Int): List<PostEntity>
}
