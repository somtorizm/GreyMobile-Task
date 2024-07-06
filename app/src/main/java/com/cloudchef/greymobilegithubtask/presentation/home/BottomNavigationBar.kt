package com.cloudchef.greymobilegithubtask.presentation.home

import android.graphics.Color
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


@Composable
fun BottomNavigation(
    currentScreenId:String,
    onItemSelected:(ScreenNav)->Unit
) {
    val items= ScreenNav.Items.list

    Row(modifier= Modifier
        .background(MaterialTheme.colorScheme.background)
        .padding(8.dp)
        .fillMaxWidth(),

        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items.forEach { item->
            BottomNavigationItem(item = item, isSelected = item.id== currentScreenId) {
                onItemSelected(item)
            }
        }
    }
}

@Composable
fun BottomNavigationItem(item: ScreenNav, isSelected: Boolean , onClick:()->Unit ){
    val primary = MaterialTheme.colorScheme.primary
    val transparent = androidx.compose.ui.graphics.Color.Transparent
    val background = if(isSelected) primary else transparent

    val contentColor=if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground

    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(background)
            .clickable(onClick = onClick)
    ){
        Row(
            modifier=Modifier
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {

            Icon(
                painter = painterResource(item.icon),
                contentDescription =null,
                tint = contentColor
            )

            AnimatedVisibility(visible = isSelected) {
                Text(
                    text = item.title,
                    color=contentColor
                )
            }
        }
    }
}
