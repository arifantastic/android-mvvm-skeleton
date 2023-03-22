package xyz.arifz.dfatest.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import xyz.arifz.dfatest.data.local.dao.UserDao
import xyz.arifz.dfatest.data.remote.RemoteDataSource
import xyz.arifz.dfatest.domain.repository.UserRepository
import xyz.arifz.dfatest.domain.usecase.GetUsersWithPostsUseCase
import xyz.arifz.dfatest.domain.usecase.GetUsersWithPostsUseCaseImpl

@Module
@InstallIn(AppModule::class)
object DomainModule {
    @Provides
    fun provideUserRepository(userDao: UserDao, remoteDataSource: RemoteDataSource): UserRepository = UserRepositoryImpl(userDao, remoteDataSource)

    @Provides
    fun provideGetUsersWithPostsUseCase(userRepository: UserRepository): GetUsersWithPostsUseCase = GetUsersWithPostsUseCaseImpl(userRepository)

    @Provides
    fun provideSaveUserUseCase(userRepository: UserRepository): SaveUserUseCase = SaveUserUseCaseImpl(userRepository)
}
