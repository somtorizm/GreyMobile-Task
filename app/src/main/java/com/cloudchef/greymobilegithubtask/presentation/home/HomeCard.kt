package com.cloudchef.greymobilegithubtask.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter

@Composable
fun HomeCard(
    title: String,
    icon: Painter,
    backgroundColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .clickable(onClick = onClick)
            .fillMaxWidth()
            .height(120.dp),
        backgroundColor = backgroundColor,
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(start = 38.dp)
        ) {
            Icon(
                painter = icon,
                contentDescription = title,
                modifier = Modifier
                    .size(40.dp)
                    .padding(4.dp)
            )
            Text(
                text = title,
                fontSize = 16.sp,
                modifier = Modifier
            )
        }
    }
}
