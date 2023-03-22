package xyz.arifz.dfatest.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ActivityComponent::class)
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): MainFragment

    @ContributesAndroidInjector
    abstract fun contributeUserFragment(): UserFragment
}

@Module
@InstallIn(ViewModelComponent::class)
class ViewModelModule {

    @Provides
    fun provideMainViewModelFactory(getUsersWithPostsUseCase: GetUsersWithPostsUseCase): MainViewModelFactory = MainViewModelFactory(getUsersWithPostsUseCase)

    @Provides
    fun provideUserViewModelFactory(saveUserUseCase: SaveUserUseCase): UserViewModelFactory = UserViewModelFactory(saveUserUseCase)
}
