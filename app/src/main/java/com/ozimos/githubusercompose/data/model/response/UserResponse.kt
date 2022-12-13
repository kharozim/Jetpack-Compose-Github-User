package com.ozimos.githubusercompose.data.model.response

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlin.math.log

data class UserResponse(
    val items: List<UserItem>
)

@Entity(tableName = "user_entity")
data class UserItem(
    @PrimaryKey val id: Long? = 0,
    @ColumnInfo(name = "login")
    val login: String ? = null,
    @ColumnInfo(name = "name")
    val name: String ? = null,
    @field:SerializedName("public_repos")
    @ColumnInfo(name = "public_repos")
    val publicRepos: Int ? = null,
    @ColumnInfo(name = "followers", defaultValue = "0")
    val followers: Int ? = null,
    @ColumnInfo(name = "following", defaultValue = "0")
    val following: Int ? = null,
    @field:SerializedName("avatar_url")
    val avatarUrl: String ? = null,
    @ColumnInfo(name = "company")
    val company: String ? = null,
    @ColumnInfo(name = "location")
    val location: String ? = null,
    @ColumnInfo(name = "email")
    val email: String ? = null,
    var isFavorite: Boolean = false
) {
    fun toModel(): UserModel = UserModel(
        id = this.id ?: 0,
        login = this.login ?: "",
        name = this.name ?: "",
        publicRepos = this.publicRepos ?: 0,
        followers = this.followers ?: 0,
        following = this.following ?: 0,
        avatarUrl = this.avatarUrl ?: "",
        company = this.company ?: "-",
        location = this.location ?: "-",
        email = this.email ?: "-",
        isFavorite = this.isFavorite
    )
}
