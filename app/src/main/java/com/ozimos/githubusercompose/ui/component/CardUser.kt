package com.ozimos.githubusercompose.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.ozimos.githubusercompose.data.model.response.UserModel

@Composable
fun CardUser(
    modifier: Modifier,
    user: UserModel,
    isFavorite: Boolean = false,
    onClick: (String) -> Unit,
) {
    Card(
        elevation = 2.dp,
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
            .padding(4.dp)
            .clickable { onClick(user.login) },
    ) {
        Column(
            modifier = modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SubcomposeAsyncImage(
                model = user.avatarUrl,
                contentDescription = null,
                modifier = modifier
                    .width(120.dp)
                    .height(120.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
                loading = {
                    CircularProgressIndicator()
                },
            )
            Spacer(modifier = Modifier.size(8.dp))

            Divider(modifier = modifier.padding(vertical = 8.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.fillMaxWidth()
            ) {
                Text(
                    text = user.login.uppercase(),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = modifier
                        .fillMaxWidth()
                        .weight(1f),
                )

                Spacer(modifier = Modifier.size(8.dp))

                Icon(
                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.Person,
                    tint = androidx.compose.material3.MaterialTheme.colorScheme.onPrimary,
                    contentDescription = "Icon Follower",
                    modifier = modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(
                            color = if (isFavorite) androidx.compose.material3.MaterialTheme.colorScheme.error
                            else androidx.compose.material3.MaterialTheme.colorScheme.primary
                        )
                        .padding(4.dp)
                        .size(14.dp)
                )
            }
        }
    }
}
