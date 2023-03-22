package xyz.arifz.dfatest.domain.usecase

import xyz.arifz.dfatest.data.local.models.PostEntity
import xyz.arifz.dfatest.domain.repository.UserRepository

class GetPostsByUserIdUseCase(private val userRepository: UserRepository) {

    suspend operator fun invoke(userId: Int): List<PostEntity> {
        return userRepository.getPostsByUserId(userId)
    }
}
