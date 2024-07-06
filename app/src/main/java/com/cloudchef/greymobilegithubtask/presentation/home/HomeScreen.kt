package com.cloudchef.greymobilegithubtask.presentation.home
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cloudchef.greymobilegithubtask.R
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
                    .padding(16.dp)
                    .weight(1f, true)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                HomeCard(
                    title = "Users",
                    icon = painterResource(id = R.drawable.user),
                    onClick = { /* Navigate to Users screen */ },
                    modifier = Modifier.weight(1f)
                )

                HomeCard(
                    title = "Repositories",
                    icon = painterResource(id = R.drawable.user),
                    onClick = { /* Navigate to Repositories screen */ },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}