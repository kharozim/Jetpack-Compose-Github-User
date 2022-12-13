package com.ozimos.githubusercompose.data.repo

import com.ozimos.githubusercompose.data.model.response.UserItem
import com.ozimos.githubusercompose.data.model.response.UserModel
import kotlinx.coroutines.flow.Flow

interface UserRepo {
    fun getListUserByName(query: String): Flow<List<UserItem>>
    fun getDetailUser(username: String): Flow<UserItem?>
    suspend fun getListUserFavorite(): List<UserItem>
    suspend fun updateUserFavorite(user: UserModel): UserItem
}