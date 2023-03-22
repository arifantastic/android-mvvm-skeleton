package xyz.arifz.dfatest.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import xyz.arifz.dfatest.data.local.models.PostEntity
import xyz.arifz.dfatest.data.local.models.UserEntity

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(posts: List<PostEntity>)

    @Query("SELECT * FROM posts WHERE userId = :userId")
    suspend fun getPostsByUserId(userId: Int): List<PostEntity>
}

