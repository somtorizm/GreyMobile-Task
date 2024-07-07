package com.cloudchef.greymobilegithubtask.presentation.search_user

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.cloudchef.greymobilegithubtask.R
import com.cloudchef.greymobilegithubtask.ui.theme.CardBackground1

@Composable
fun TagItem(tag: String) {
    Text(
        text = tag,
        color = Color.Black,
        maxLines = 1,
        modifier = Modifier
            .background(color = CardBackground1, shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    )
}

@Composable
fun TagsRow(tags: List<String>) {
    Row(
        modifier = Modifier.padding(top = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        tags.forEach { tag ->
            TagItem(tag = tag)
        }
    }
}

@Composable
fun Header(title: String, subtitle: String, stars: Int, language: String, url: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberAsyncImagePainter(url),
            contentDescription = "Profile Image",
            modifier = Modifier
                .size(30.dp)
                .clip(CircleShape)
                .background(Color.Gray)
        )
        Spacer(modifier = Modifier.width(8.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = title, fontWeight = FontWeight.Bold, maxLines = 1)
            Text(text = subtitle, color = Color.Gray, maxLines = 1)
        }
        Spacer(modifier = Modifier.width(8.dp))

        Column(horizontalAlignment = Alignment.End) {
            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(painter = painterResource(id = R.drawable.star), contentDescription = "Stars")
                Text(text = stars.toString(), fontWeight = FontWeight.Bold)

                Icon(painter = painterResource(id = R.drawable.ellipse), contentDescription = "Stars", tint = Color.Green)
                Text(
                    text = language,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}

@Composable
fun Description(text: String) {
    Text(
        text = text,
        maxLines = 2,
        modifier = Modifier.padding(top = 8.dp),
    )
}

@Composable
fun RepoCard(title: String, subtitle: String, url: String, stars: Int, language: String, description: String, tags: List<String>, onClick: (id: String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(top = 10.dp)
            .clickable { onClick(title) },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.inverseOnSurface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Header(title = title, subtitle = subtitle, url = url, stars = stars, language = language)
            Spacer(modifier = Modifier.height(10.dp))
            Description(text = description)
            TagsRow(tags = tags)
        }
    }
}