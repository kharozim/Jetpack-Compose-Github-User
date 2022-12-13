package com.ozimos.githubusercompose.data.model.response

data class UserModel(
    val id: Long,
    val login: String,
    val name: String,
    val publicRepos: Int,
    val followers: Int,
    val following: Int,
    val avatarUrl: String,
    val company: String,
    val location: String,
    val email: String,
    var isFavorite: Boolean
) {
    fun toResponse() = UserItem(
        id = id,
        login = login,
        name = name,
        publicRepos = publicRepos,
        followers = followers,
        following = following,
        avatarUrl = avatarUrl,
        company = company,
        location = location,
        email = email,
        isFavorite = isFavorite
    )
}
