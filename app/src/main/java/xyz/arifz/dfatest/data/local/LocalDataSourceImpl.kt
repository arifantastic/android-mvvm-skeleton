package xyz.arifz.dfatest.data.local

import xyz.arifz.dfatest.data.local.dao.UserDao
import xyz.arifz.dfatest.data.local.models.PostEntity
import xyz.arifz.dfatest.data.local.models.UserEntity

class LocalDataSourceImpl(private val userDao: UserDao, private val postDao: PostDao) : LocalDataSource {

    override suspend fun saveUsers(users: List<UserEntity>) {
        userDao.insertAll(users)
    }

    override suspend fun savePosts(posts: List<PostEntity>) {
        postDao.insertAll(posts)
    }

    override suspend fun getUsers(): List<UserEntity> {
        return userDao.getAll()
    }

    override suspend fun getPostsByUserId(userId: Int): List<PostEntity> {
        return postDao.getPostsByUserId(userId)
    }
}
