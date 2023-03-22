package xyz.arifz.dfatest.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import xyz.arifz.dfatest.data.local.dao.UserDao
import xyz.arifz.dfatest.data.local.database.AppDatabase
import xyz.arifz.dfatest.data.remote.ApiService
import xyz.arifz.dfatest.data.remote.RemoteDataSource
import xyz.arifz.dfatest.data.remote.RemoteDataSourceImpl

@Module
@InstallIn(AppModule::class)
object DataModule {
    @Provides
    fun provideApiService(): ApiService = Retrofit.Builder()
        .baseUrl("https:arifz.xyz/api/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)

    @Provides
    fun provideAppDatabase(context: Context): AppDatabase = Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java,
        "my_db.db"
    ).build()

    @Provides
    fun provideUserDao(appDatabase: AppDatabase): UserDao = appDatabase.userDao()

    @Provides
    fun provideRemoteDataSource(apiService: ApiService): RemoteDataSource = RemoteDataSourceImpl(apiService)
}