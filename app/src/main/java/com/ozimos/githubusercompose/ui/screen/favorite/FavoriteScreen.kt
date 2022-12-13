package com.ozimos.githubusercompose.ui.screen.favorite

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ozimos.githubusercompose.ui.UserViewModel
import com.ozimos.githubusercompose.ui.common.UiState
import com.ozimos.githubusercompose.ui.component.EmptyState
import com.ozimos.githubusercompose.ui.component.LoadingState
import com.ozimos.githubusercompose.ui.screen.favorite.component.SectionListUser

@Composable
fun FavoriteScreen(
    viewModel: UserViewModel = hiltViewModel(),
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {

    LaunchedEffect(key1 = true, block = {
        viewModel.getListFavorite()
    })

    val userObserver by viewModel.listUserFav.collectAsState(UiState.Loading)
    userObserver.let { state ->
        when (state) {
            is UiState.Loading -> {
                LoadingState(modifier = modifier)
            }
            is UiState.Error -> {
                EmptyState(modifier = modifier, message = state.message)
            }
            is UiState.Success -> {
                Log.d("TAG", "getListFavorite: ${state.data.size}")
                if (state.data.isEmpty()) {
                    EmptyState(modifier = modifier)
                } else {
                    Column(
                        modifier = modifier
                            .fillMaxSize()
                            .background(color = MaterialTheme.colorScheme.background)
                    ) {
                        SectionListUser(
                            data = state.data,
                            modifier = modifier,
                            navHostController = navHostController
                        )
                    }

                }
            }
        }
    }
}