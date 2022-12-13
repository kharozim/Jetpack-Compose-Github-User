package com.ozimos.githubusercompose.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ozimos.githubusercompose.data.model.response.UserItem

@Dao
interface UserDao {

    @Query("SELECT * FROM user_entity")
    suspend fun getAllFavorite(): List<UserItem>

    @Query("SELECT * FROM user_entity WHERE login = (:username)")
    suspend fun getDetailUser(username: String): UserItem?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUserFav(vararg user: UserItem)

    @Delete
    suspend fun delete(user: UserItem)
}