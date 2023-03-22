package xyz.arifz.dfatest.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import xyz.arifz.dfatest.data.local.dao.PostDao
import xyz.arifz.dfatest.data.local.dao.UserDao
import xyz.arifz.dfatest.data.local.models.PostEntity
import xyz.arifz.dfatest.data.local.models.UserEntity

@Database(entities = [UserEntity::class, PostEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun postDao(): PostDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(LOCK) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            AppDatabase::class.java, "my_db.db"
        ).build()
    }
}
