package com.cloudchef.greymobilegithubtask.presentation.home
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cloudchef.greymobilegithubtask.R
import com.cloudchef.greymobilegithubtask.presentation.user_detail.GithubUserViewModel

@Composable
fun HomeScreen(
    modifier: Modifier,
    viewModel: GithubUserViewModel = hiltViewModel()
) {
    var selectedIndex by remember { mutableIntStateOf(0) }
    var currentScreen by remember { mutableStateOf<ScreenNav>(ScreenNav.Home) }

    Scaffold(
        modifier = modifier,
        bottomBar = {
            BottomNavigation(currentScreenId = currentScreen.id) { it
               currentScreen = it
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(20.dp)
        ) {
            Text(
                text = "Home",
                fontSize = 18.sp,
                modifier = Modifier
            )

            Spacer(modifier = Modifier.height(40.dp))

            Row(
                modifier = Modifier
                    .weight(1f, true)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                HomeCard(
                    title = "Users",
                    icon = painterResource(id = R.drawable.user),
                    onClick = { /* Navigate to Users screen */ },
                    backgroundColor = colorResource(id = R.color.light_grey),
                    modifier = Modifier.weight(1f)
                )

                HomeCard(
                    title = "Repositories",
                    icon = painterResource(id = R.drawable.user),
                    onClick = { /* Navigate to Repositories screen */ },
                    backgroundColor = colorResource(id = R.color.light_purple),
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}