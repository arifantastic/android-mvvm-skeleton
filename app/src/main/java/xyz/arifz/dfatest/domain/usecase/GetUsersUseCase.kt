package xyz.arifz.dfatest.domain.usecase

import xyz.arifz.dfatest.data.local.models.UserEntity
import xyz.arifz.dfatest.domain.repository.UserRepository

class GetUsersUseCase(private val userRepository: UserRepository) {

    suspend operator fun invoke(): Result<List<UserEntity>> {
        return userRepository.getUsers()
    }
}
