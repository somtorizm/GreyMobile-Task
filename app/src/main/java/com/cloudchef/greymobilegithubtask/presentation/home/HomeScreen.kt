package com.cloudchef.greymobilegithubtask.presentation.home
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cloudchef.greymobilegithubtask.presentation.user_detail.GithubUserViewModel

@Composable
fun HomeScreen(
    modifier: Modifier,
    viewModel: GithubUserViewModel = hiltViewModel()
) {
    var selectedIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        modifier = modifier,
        bottomBar = {
            BottomNavigationBar(selectedIndex) { selectedIndex = it }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                HomeCard(
                    title = "Users",
                    icon = Icons.Default.Person,
                    backgroundColor = Color(0xFFE0F7FA),
                    onClick = { /* Navigate to Users screen */ }
                )
                HomeCard(
                    title = "Repositories",
                    icon = Icons.Default.List,
                    backgroundColor = Color(0xFFF3E5F5),
                    onClick = { /* Navigate to Repositories screen */ }
                )
            }
        }
    }
}