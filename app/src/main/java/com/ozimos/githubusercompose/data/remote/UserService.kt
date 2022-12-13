package com.ozimos.githubusercompose.data.remote

import com.ozimos.githubusercompose.data.model.response.UserItem
import com.ozimos.githubusercompose.data.model.response.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {
    @GET("/search/users")
    suspend fun searchUser(
        @Query("q") search: String
    ): Response<UserResponse>

    @GET("/users/{username}")
    suspend fun getDetailUser(
        @Path("username") username: String
    ): Response<UserItem>

}