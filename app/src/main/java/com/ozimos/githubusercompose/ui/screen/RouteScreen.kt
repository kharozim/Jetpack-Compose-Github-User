package com.ozimos.githubusercompose.ui.screen

sealed class RouteScreen(val route: String) {
    object Home : RouteScreen("home")
    object Profile : RouteScreen("profile")
    object Favorite : RouteScreen("favorite")
    object DetailUser : RouteScreen("detail/{username}") {
        const val usernameArgs = "username"
        fun open(username: String) = "detail/$username"
    }
}