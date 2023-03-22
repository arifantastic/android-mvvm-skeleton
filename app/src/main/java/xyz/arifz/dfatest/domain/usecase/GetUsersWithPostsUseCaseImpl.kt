package xyz.arifz.dfatest.domain.usecase

class GetUsersWithPostsUseCaseImpl(private val userRepository: UserRepository) : GetUsersWithPostsUseCase {

    override suspend fun invoke(): Result<List<UserWithPosts>> {
        val users = userRepository.getUsers()

        return when (users) {
            is Result.Success -> {
                val userWithPostsList = mutableListOf<UserWithPosts>()

                for (user in users.data) {
                    val posts = userRepository.getPostsByUserId(user.id)
                    userWithPostsList.add(UserWithPosts(user, posts))
                }

                Result.Success(userWithPostsList)
            }
            is Result.Error -> Result.Error(users.exception)
        }
    }
}
