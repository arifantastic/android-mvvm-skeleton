package xyz.arifz.dfatest.domain.usecase

import xyz.arifz.dfatest.data.local.models.UserWithPosts

interface GetUsersWithPostsUseCase {

    suspend operator fun invoke(): Result<List<UserWithPosts>>
}
