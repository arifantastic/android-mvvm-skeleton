package xyz.arifz.dfatest.data.local.models

import xyz.arifz.dfatest.presentation.utils.UserEntity

data class UserWithPosts(
    val user: UserEntity,
    val posts: List<PostEntity>
)