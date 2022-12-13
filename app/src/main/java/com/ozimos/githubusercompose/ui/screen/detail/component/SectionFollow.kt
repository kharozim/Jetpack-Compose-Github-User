package com.ozimos.githubusercompose.ui.screen.detail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun SectionFollow(text: String, modifier: Modifier) {
    Row(
        modifier = modifier
            .clip(CircleShape)
            .background(color = androidx.compose.material3.MaterialTheme.colorScheme.primary)
            .padding(vertical = 8.dp, horizontal = 10.dp)
    ) {
        Text(
            text = text,
            color = androidx.compose.material3.MaterialTheme.colorScheme.onPrimary
        )
    }
}