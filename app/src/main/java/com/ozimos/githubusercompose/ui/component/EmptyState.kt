package com.ozimos.githubusercompose.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ozimos.githubusercompose.R

@Composable
fun EmptyState(
    message: String = stringResource(id = R.string.no_data_found_message),
    modifier: Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize(),
    ) {
        Image(
            painter = painterResource(id = R.drawable.il_no_data),
            contentDescription = "no data state"
        )
        Spacer(modifier = modifier.size(12.dp))
        Text(
            text = stringResource(id = R.string.no_data_found),
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = modifier.size(4.dp))
        Text(text = message)
    }
}