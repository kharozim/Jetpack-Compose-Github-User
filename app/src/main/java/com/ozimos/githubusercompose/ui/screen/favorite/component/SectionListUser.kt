package com.ozimos.githubusercompose.ui.screen.favorite.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ozimos.githubusercompose.data.model.response.UserModel
import com.ozimos.githubusercompose.ui.component.CardUser
import com.ozimos.githubusercompose.ui.screen.RouteScreen

@Composable
fun SectionListUser(
    data: List<UserModel>,
    modifier: Modifier,
    navHostController: NavHostController
) {

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 130.dp),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(data, key = { it.id }) { item ->
            CardUser(modifier = modifier, user = item, isFavorite = true) { username ->
                navHostController.navigate(RouteScreen.DetailUser.open(username))
            }
        }
    }
}
