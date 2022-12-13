package com.ozimos.githubusercompose.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.ozimos.githubusercompose.ui.UserViewModel
import com.ozimos.githubusercompose.ui.common.UiState
import com.ozimos.githubusercompose.ui.component.EmptyState
import com.ozimos.githubusercompose.ui.component.LoadingState
import com.ozimos.githubusercompose.ui.screen.home.component.SectionListUser
import com.ozimos.githubusercompose.ui.screen.home.component.SectionSearch

@Composable
fun HomeScreen(
    userViewModel: UserViewModel,
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = androidx.compose.material3.MaterialTheme.colorScheme.background)
    ) {
        SectionSearch(
            modifier = modifier,
            onSubmit = {
                userViewModel.getListUser(it)
            },
            onClearText = {
                userViewModel.clearData()
            },
        )

        userViewModel.listUser.collectAsState().value.let { uiState ->
            when (uiState) {
                is UiState.Loading -> {
                    LoadingState(modifier = modifier)
                }
                is UiState.Error -> {
                    EmptyState(modifier = modifier)
                }
                is UiState.Success -> {
                    if (uiState.data.isEmpty()) {
                        EmptyState(modifier = modifier)
                    } else {
                        SectionListUser(
                            data = uiState.data,
                            modifier = modifier,
                            navHostController = navHostController
                        )
                    }
                }
            }
        }
    }
}


