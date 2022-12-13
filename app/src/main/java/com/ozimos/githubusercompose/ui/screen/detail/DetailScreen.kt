package com.ozimos.githubusercompose.ui.screen.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.SubcomposeAsyncImage
import com.ozimos.githubusercompose.R
import com.ozimos.githubusercompose.data.model.response.UserItem
import com.ozimos.githubusercompose.data.model.response.UserModel
import com.ozimos.githubusercompose.ui.UserViewModel
import com.ozimos.githubusercompose.ui.common.UiState
import com.ozimos.githubusercompose.ui.component.EmptyState
import com.ozimos.githubusercompose.ui.component.LoadingState
import com.ozimos.githubusercompose.ui.component.TopBar
import com.ozimos.githubusercompose.ui.screen.detail.component.SectionBiodata
import com.ozimos.githubusercompose.ui.screen.detail.component.SectionFollow

@Composable
fun DetailScreen(
    username: String,
    modifier: Modifier,
    navHostController: NavHostController,
    viewModel: UserViewModel = hiltViewModel()
) {

    if (username.isNotEmpty()) {
        viewModel.getDetailUser(username)
    }

    val detailUser by viewModel.detailUser.collectAsState()

    Scaffold(
        topBar = {
            TopBar(
                elevation = 0.dp,
                title = stringResource(id = R.string.detail),
                modifier = modifier,
                isBackEnable = true,
                onClick = { navHostController.navigateUp() }
            )
        },
        backgroundColor = androidx.compose.material3.MaterialTheme.colorScheme.background
    ) { paddingValue ->

        detailUser.let { user ->
            when (user) {
                is UiState.Loading -> {
                    LoadingState(modifier = modifier)
                }
                is UiState.Error -> {
                    EmptyState(modifier = modifier, message = user.message)
                }
                is UiState.Success -> {
                    DataSection(modifier = modifier.padding(paddingValue), user = user.data)
                }
            }
        }
    }
}

@Composable
private fun DataSection(
    modifier: Modifier = Modifier,
    user: UserModel,
    viewModel: UserViewModel = hiltViewModel()
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Card(
            elevation = 2.dp,
            shape = RoundedCornerShape(12.dp),
        ) {

            Column(modifier = modifier.padding(16.dp)) {
                val screen = LocalConfiguration.current

                Row(
                    modifier = modifier
                        .height(screen.screenHeightDp.dp / 3)
                        .padding(horizontal = 12.dp)
                ) {
                    Column(
                        modifier = modifier
                            .clip(RoundedCornerShape(12.dp))

                    ) {
                        SubcomposeAsyncImage(
                            model = user.avatarUrl,
                            contentDescription = stringResource(id = R.string.image_detail_user),
                            contentScale = ContentScale.Crop,
                            loading = {
                                CircularProgressIndicator()
                            },
                            modifier = modifier
                                .width(180.dp)
                                .clip(RoundedCornerShape(8.dp)),
                        )
                    }

                    Spacer(modifier = modifier.size(12.dp))

                    Column {
                        Text(
                            text = user.name.uppercase(),
                            style = androidx.compose.material3.MaterialTheme.typography.titleMedium,
                            modifier = modifier.weight(1f),
                            fontWeight = FontWeight.Bold,
                        )
                        SectionBiodata(
                            image = Icons.Default.Person,
                            title = stringResource(id = R.string.username),
                            subtitle = "@${user.login}"
                        )
                        SectionBiodata(
                            image = Icons.Default.Email,
                            title = stringResource(id = R.string.email),
                            subtitle = user.email
                        )
                        SectionBiodata(
                            image = Icons.Default.Place,
                            title = stringResource(id = R.string.place),
                            subtitle = user.location
                        )
                    }
                }
                Spacer(modifier = modifier.size(12.dp))
                Row(modifier = modifier.padding(horizontal = 12.dp)) {
                    SectionFollow(text = "FOLLOWER ${user.followers}", modifier = modifier)
                    Spacer(modifier = modifier.size(12.dp))
                    SectionFollow(text = "FOLLOWING ${user.following}", modifier = modifier)
                    Spacer(modifier = modifier.size(12.dp))
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = modifier
                            .weight(1f)
                            .clickable {
                                viewModel.updateUserFavorite(user)
                            },
                    ) {
                        Icon(
                            imageVector = if (user.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = stringResource(id = R.string.icon_favorite),
                            modifier = modifier,
                            tint = androidx.compose.material3.MaterialTheme.colorScheme.error
                        )
                        Text(text = stringResource(id = if (user.isFavorite) R.string.favorite else R.string.click_me))
                    }
                }
            }
        }

    }

}


@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    MaterialTheme() {
        DataSection(
            user = UserItem(
                name = "Imron Nanda Marpaung",
                avatarUrl = "https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"
            ).toModel()
        )
    }
}