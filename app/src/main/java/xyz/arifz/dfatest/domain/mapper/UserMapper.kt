package xyz.arifz.dfatest.domain.mapper

import xyz.arifz.dfatest.data.local.models.UserEntity
import xyz.arifz.dfatest.data.local.models.UserResponse

object UserMapper {

    fun mapToEntity(userResponse: UserResponse): UserEntity {
        return UserEntity(
            id = userResponse.id,
            name = userResponse.name,
            email = userResponse.email
        )
    }

    fun mapToEntityList(userResponses: List<UserResponse>): List<UserEntity> {
        return userResponses.map { mapToEntity(it) }
    }
}
