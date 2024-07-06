package com.cloudchef.greymobilegithubtask.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cloudchef.greymobilegithubtask.R


@Composable
fun BottomNavigation(
    currentScreenId:String,
    onItemSelected:(ScreenNav)->Unit
) {
    val currentScreenIdState by rememberUpdatedState(currentScreenId)
    val onItemSelectedState by rememberUpdatedState(onItemSelected)
    val items = ScreenNav.Items.list

    Card(elevation = 10.dp, modifier = Modifier.height(100.dp)) {
        Row(modifier= Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(8.dp)
            .fillMaxWidth(),

            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEach { item->
                BottomNavigationItem(item = item, isSelected = item.id == currentScreenIdState) {
                    onItemSelectedState(item)
                }
            }
        }
    }
}

@Composable
fun BottomNavigationItem(item: ScreenNav, isSelected: Boolean, onClick:()->Unit ){
    val primary = colorResource(id = R.color.btn_color)
    val transparent = androidx.compose.ui.graphics.Color.Transparent
    val white = androidx.compose.ui.graphics.Color.White
    val background = if(isSelected) primary else transparent
    val contentColor = if (isSelected) white else MaterialTheme.colorScheme.onBackground

    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(background)
            .clickable(onClick = onClick)
    ){
        Row(
            modifier= Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {

            Icon(
                painter = painterResource(if(isSelected) item.icon else item.unselectedIcon),
                contentDescription =null,
                tint = contentColor
            )

            Text(
                text = item.title,
                color=contentColor
            )
        }
    }
}
