package com.ozimos.githubusercompose.data.repo

import com.ozimos.githubusercompose.data.local.UserDao
import com.ozimos.githubusercompose.data.model.response.UserItem
import com.ozimos.githubusercompose.data.model.response.UserModel
import com.ozimos.githubusercompose.data.remote.UserService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class IUserRepo @Inject constructor(private val local: UserDao, private val remote: UserService) :
    UserRepo {
    override fun getListUserByName(query: String): Flow<List<UserItem>> {
        return flow {
            val result = remote.searchUser(query)

            if (result.isSuccessful) {
                emit(result.body()?.items ?: emptyList())
            } else {
                emit(emptyList())
            }
        }
    }

    override fun getDetailUser(username: String): Flow<UserItem?> {
        return flow {
            val response = remote.getDetailUser(username = username)
            if (response.isSuccessful) {
                val local = local.getDetailUser(response.body()?.login ?: "")
                val result = local ?: response.body()
                emit(result)
            } else {
                emit(null)
            }
        }
    }

    override suspend fun getListUserFavorite(): List<UserItem> {
        return local.getAllFavorite()
    }

    override suspend fun updateUserFavorite(user: UserModel): UserItem {
        val userLocal = local.getDetailUser(user.login)
        if (userLocal != null) {
            user.isFavorite = false
            local.delete(user.toResponse())
        } else {
            user.isFavorite = true
            local.addUserFav(user.toResponse())
        }
        return user.toResponse()
    }
}