package com.ozimos.githubusercompose.ui.screen.profile

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ozimos.githubusercompose.R
import com.ozimos.githubusercompose.ui.theme.Blue100
import com.ozimos.githubusercompose.ui.theme.BlueGrey100
import com.ozimos.githubusercompose.ui.theme.GithubUserCompose

@Composable
fun ProfileScreen(modifier: Modifier = Modifier
    .background(color = androidx.compose.material3.MaterialTheme.colorScheme.background)
) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Card(
            shape = RoundedCornerShape(20.dp),
            elevation = 2.dp,
            modifier = modifier
                .padding(24.dp)
                .fillMaxWidth()
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(vertical = 42.dp, horizontal = 16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_profile),
                    contentScale = ContentScale.Crop,
                    contentDescription = "Image Profile",
                    modifier = modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .border(width = 4.dp, color = Color.Gray, shape = CircleShape)
                )
                Spacer(modifier = modifier.size(18.dp))
                Text(
                    text = stringResource(id = R.string.my_name),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.body1.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Divider(Modifier.padding(vertical = 24.dp))
                SosmedSection(modifier = modifier)
            }
        }
    }
}

@Composable
private fun SosmedSection(modifier: Modifier) {
    val context = LocalContext.current

    Sosmed(
        image = painterResource(id = R.drawable.ic_email),
        contentDescription = "Icon Email",
        text = stringResource(id = R.string.my_email),
        modifier = modifier,
        onClick = {
            Toast.makeText(context, "Email", Toast.LENGTH_SHORT).show()
        }
    )
    Spacer(modifier = modifier.size(12.dp))

    Sosmed(
        image = painterResource(id = R.drawable.ic_linkedin),
        contentDescription = "Icon Linkedin",
        text = "@" + stringResource(id = R.string.my_linkedin),
        backgroundColor = Blue100,
        modifier = modifier,
        onClick = {
            Toast.makeText(context, "Linkedin", Toast.LENGTH_SHORT).show()
        }
    )
    Spacer(modifier = modifier.size(12.dp))

    Sosmed(
        image = painterResource(id = R.drawable.ic_github),
        contentDescription = "Icon Github",
        text = "@" + stringResource(id = R.string.my_github),
        backgroundColor = BlueGrey100,
        modifier = modifier,
        onClick = {
            Toast.makeText(context, "Github", Toast.LENGTH_SHORT).show()
        }
    )
}

@Composable
private fun Sosmed(
    image: Painter,
    contentDescription: String,
    text: String,
    modifier: Modifier,
    backgroundColor: Color = MaterialTheme.colors.background,
    onClick: () -> Unit
) {

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .shadow(elevation = 2.dp, shape = CircleShape, clip = true)
            .clickable { onClick() }
            .background(color = backgroundColor)
            .padding(8.dp)

    ) {
        Image(
            painter = image,
            contentDescription = contentDescription,
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(text = text, fontSize = 10.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    GithubUserCompose() {
        ProfileScreen()
    }
}