package com.ozimos.githubusercompose.ui.screen.profile

import android.content.Context
import android.content.Intent
import android.net.Uri
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
fun ProfileScreen(
    modifier: Modifier = Modifier,
) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(color = androidx.compose.material3.MaterialTheme.colorScheme.background)
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
                    contentDescription = stringResource(id = R.string.image_profile),
                    modifier = modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .border(width = 4.dp, color = Color.Gray, shape = CircleShape)
                )
                Spacer(modifier = modifier.size(18.dp))
                Text(
                    text = stringResource(id = R.string.my_name),
                    textAlign = TextAlign.Center,
                    style = androidx.compose.material3.MaterialTheme.typography.titleMedium.copy(
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
private fun SosmedSection(context: Context = LocalContext.current, modifier: Modifier) {

    Sosmed(
        image = painterResource(id = R.drawable.ic_email),
        contentDescription = stringResource(id = R.string.icon_email),
        text = stringResource(id = R.string.my_email),
        modifier = modifier,
        onClick = {
            sendEmail(context, "Send Email")
        }
    )


    Sosmed(
        image = painterResource(id = R.drawable.ic_linkedin),
        contentDescription = stringResource(id = R.string.icon_linkedin),
        text = "@" + stringResource(id = R.string.my_linkedin),
        backgroundColor = Blue100,
        modifier = modifier,
        onClick = {
            val webIntent =
                Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/kharozim/"))
            context.startActivity(Intent(webIntent))
        }
    )

    Sosmed(
        image = painterResource(id = R.drawable.ic_github),
        contentDescription = stringResource(id = R.string.icon_github),
        text = "@" + stringResource(id = R.string.my_github),
        backgroundColor = BlueGrey100,
        modifier = modifier,
        onClick = {
            val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/kharozim/"))
            context.startActivity(Intent(webIntent))
        }
    )
}

private fun sendEmail(context: Context, summary: String) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_EMAIL, context.getString(R.string.my_email))
        putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.send_email))
        putExtra(Intent.EXTRA_TEXT, summary)
    }

    context.startActivity(
        Intent.createChooser(
            intent,
            context.getString(R.string.send_email)
        )
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
            .padding(vertical = 6.dp)
            .fillMaxWidth()
            .shadow(elevation = 1.dp, shape = CircleShape)
            .clickable { onClick() }
            .background(color = backgroundColor)
            .padding(8.dp)

    ) {
        Image(
            painter = image,
            contentDescription = contentDescription,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(text = text, fontSize = 12.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    GithubUserCompose {
        ProfileScreen()
    }
}