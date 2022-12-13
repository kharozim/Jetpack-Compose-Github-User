package com.ozimos.githubusercompose.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ozimos.githubusercompose.R

@Composable
fun TopBar(
    title: String,
    isBackEnable: Boolean = false,
    onClick: () -> Unit = {},
    modifier: Modifier,
    elevation: Dp = AppBarDefaults.TopAppBarElevation
) {
    TopAppBar(
        elevation = elevation,
        backgroundColor = MaterialTheme.colorScheme.background,
        title = { Text(text = title, color = MaterialTheme.colorScheme.primary) },
        navigationIcon =
        if (isBackEnable) {
            {
                Spacer(modifier = Modifier.size(12.dp))
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(id = R.string.my_email),
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = modifier
                        .clip(CircleShape)
                        .clickable { onClick() }
                        .padding(8.dp)
                )
            }
        } else null
    )
}