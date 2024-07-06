package com.cloudchef.greymobilegithubtask.presentation.home

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun BottomNavigationBar(
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    BottomNavigation {
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = selectedIndex == 0,
            onClick = { onItemSelected(0) }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.List, contentDescription = "Repositories") },
            label = { Text("Repositories") },
            selected = selectedIndex == 1,
            onClick = { onItemSelected(1) }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Person, contentDescription = "Users") },
            label = { Text("Users") },
            selected = selectedIndex == 2,
            onClick = { onItemSelected(2) }
        )
    }
}