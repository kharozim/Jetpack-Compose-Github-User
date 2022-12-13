package com.ozimos.githubusercompose.ui.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ozimos.githubusercompose.R
import com.ozimos.githubusercompose.ui.UserViewModel
import com.ozimos.githubusercompose.ui.screen.detail.DetailScreen
import com.ozimos.githubusercompose.ui.screen.favorite.FavoriteScreen
import com.ozimos.githubusercompose.ui.screen.home.HomeScreen
import com.ozimos.githubusercompose.ui.screen.profile.ProfileScreen
import com.ozimos.githubusercompose.ui.theme.GithubUserCompose
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GithubUserCompose {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = androidx.compose.material3.MaterialTheme.colorScheme.background
                ) {
                    MainApp()
                }
            }
        }
    }
}


@Composable
fun MainApp(
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController(),
    viewModel: UserViewModel = hiltViewModel()
) {

    Scaffold(bottomBar = {
        BottomBar(navHostController)
    }) { paddingContent ->

        NavHost(
            navController = navHostController,
            startDestination = RouteScreen.Home.route,
            modifier = modifier.padding(paddingContent)
        ) {

            composable(RouteScreen.Home.route) {
                HomeScreen(
                    modifier = modifier,
                    userViewModel = viewModel,
                    navHostController = navHostController
                )
            }
            composable(RouteScreen.Favorite.route) {
                FavoriteScreen(navHostController = navHostController)
            }
            composable(RouteScreen.Profile.route) {
                ProfileScreen()
            }

            composable(
                route = RouteScreen.DetailUser.route, arguments = listOf(
                    navArgument(RouteScreen.DetailUser.usernameArgs) {
                        type = NavType.StringType
                    },
                )
            ) {
                val username = it.arguments?.getString(RouteScreen.DetailUser.usernameArgs)
                DetailScreen(
                    username = username ?: "",
                    modifier = modifier,
                    navHostController = navHostController,
                )
            }
        }
    }
}

@Composable
fun BottomBar(
    navHostController: NavHostController, modifier: Modifier = Modifier
) {

    val navBackStackEntry by navHostController.currentBackStackEntryAsState()

    val listMenuBottomBar = listOf(
        BottomBarMenu(
            title = stringResource(id = R.string.home),
            icon = Icons.Default.Home,
            screen = RouteScreen.Home,
            contentDescription = stringResource(id = R.string.home_page)
        ), BottomBarMenu(
            title = stringResource(id = R.string.favorite),
            icon = Icons.Default.Favorite,
            screen = RouteScreen.Favorite,
            contentDescription = stringResource(id = R.string.favorite_page)

        ), BottomBarMenu(
            title = stringResource(id = R.string.profile),
            icon = Icons.Default.Person,
            screen = RouteScreen.Profile,
            contentDescription = stringResource(id = R.string.about_page)
        )
    )

    if (navBackStackEntry?.destination?.route != RouteScreen.DetailUser.route) {
        BottomBarNavigation(
            items = listMenuBottomBar, navHostController = navHostController, modifier = modifier
        )
    }

}

@Composable
fun BottomBarNavigation(
    items: List<BottomBarMenu>, navHostController: NavHostController, modifier: Modifier
) {
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        containerColor = androidx.compose.material3.MaterialTheme.colorScheme.primary,
        modifier = modifier,
    ) {
        items.map {
            NavigationBarItem(
                label = { Text(text = it.title) },
                icon = {
                    Icon(
                        imageVector = it.icon, contentDescription = it.contentDescription
                    )
                },
                selected = currentRoute == it.screen.route,
                onClick = {
                    navHostController.navigate(it.screen.route) {
                        popUpTo(navHostController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                },
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    GithubUserCompose {
        MainApp()
    }
}

data class BottomBarMenu(
    val title: String,
    val icon: ImageVector,
    val screen: RouteScreen,
    val contentDescription: String
)

