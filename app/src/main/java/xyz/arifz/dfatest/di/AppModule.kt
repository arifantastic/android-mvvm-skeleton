package xyz.arifz.dfatest.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn

@Module
@InstallIn(AppModule::class)
object AppModule {

    @Provides
    fun provideContext(application: Application): Context = application.applicationContext
}