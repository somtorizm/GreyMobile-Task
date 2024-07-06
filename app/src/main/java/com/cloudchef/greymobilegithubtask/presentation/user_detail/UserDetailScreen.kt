package com.cloudchef.greymobilegithubtask.presentation.user_detail

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.cloudchef.greymobilegithubtask.R

@Composable
fun UserProfileScreen() {
    Column(modifier = Modifier.padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_left),
                contentDescription = "back",
                tint = Color.Gray,
                )

            Text(
                text = "Users",
                fontSize = 18.sp,
                modifier = Modifier
            )
        }
        
        Spacer(modifier = Modifier.height(40.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            
            Image(
                painter = rememberAsyncImagePainter("https://example.com/avatar.jpg"),
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = "Paige Brown", style = MaterialTheme.typography.bodyMedium)
                Text(text = "DynamicWebPaige", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "This is a random bio, which will be replaced with actual content",
            style = MaterialTheme.typography.labelMedium
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(painter = painterResource(id = R.drawable.location), contentDescription = null, tint = Color.Gray)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Lagos, Nigeria", style = MaterialTheme.typography.bodySmall, color = Color.Gray)

            Spacer(modifier = Modifier.width(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(painter = painterResource(id = R.drawable.link), contentDescription = null, tint = Color.Gray)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "http://paige.com", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            }
        }

        
        Spacer(modifier = Modifier.height(16.dp))

        Row (horizontalArrangement = Arrangement.spacedBy(10.dp)){
            Icon(painter = painterResource(id = R.drawable.people), contentDescription = null, tint = Color.Gray)
            Text(text = "400 followers", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = "30 following", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
        }
        Spacer(modifier = Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            Text(text = "Repositories", style = MaterialTheme.typography.bodySmall)
            Text(text = "200", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
        }
        Spacer(modifier = Modifier.height(16.dp))


        CustomLine()
    }
}

@Composable
fun CustomLine() {
    val color = if(isSystemInDarkTheme()) Color.White else Color.Gray
    val color1 = Color.Gray

    Canvas(modifier = Modifier.fillMaxWidth()) {
        val startX = 0f
        val endX = size.width
        val startY = size.height / 3
        val endY = size.height / 3

        val initialThickness = 5f
        val finalThickness = 3f


        val brush = Brush.horizontalGradient(
            colors = listOf(
                color.copy(alpha = 1f),
                color1.copy(alpha = 0f)
            )
        )

        drawLine(
            brush = brush,
            start = Offset(startX, startY),
            end = Offset(startX + (endX / 3), endY),
            strokeWidth = initialThickness
        )

        drawLine(
            color = color1,
            start = Offset(startX + (endX / 3), startY),
            end = Offset(endX, endY),
            strokeWidth = finalThickness
        )
    }
}