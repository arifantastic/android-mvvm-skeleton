package com.example.android_mvvm_skeleton.di

import com.example.android_mvvm_skeleton.posts.view.PostFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributePostFragment(): PostFragment
}
