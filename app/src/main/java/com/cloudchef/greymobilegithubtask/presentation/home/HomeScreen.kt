package com.cloudchef.greymobilegithubtask.presentation.home
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cloudchef.greymobilegithubtask.R
import com.cloudchef.greymobilegithubtask.presentation.ScreenNav
import com.cloudchef.greymobilegithubtask.presentation.user_detail.GithubUserViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: GithubUserViewModel = hiltViewModel()
) {
    HomeScreenView(modifier = Modifier
        .padding(horizontal = 20.dp), navController)
}

@Composable
fun HomeScreenView(modifier: Modifier, navController: NavController) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 40.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
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
                icon = painterResource(id = R.drawable.user_card),
                onClick = {
                    navController.navigate(ScreenNav.Users.id)
                },
                backgroundColor = colorResource(id = R.color.light_grey),
                modifier = Modifier.weight(1f)
            )

            HomeCard(
                title = "Repositories",
                icon = painterResource(id = R.drawable.repo_icon),
                onClick = {
                    navController.navigate(ScreenNav.Search.id)
                          },
                backgroundColor = colorResource(id = R.color.light_purple),
                modifier = Modifier.weight(1f)
            )
        }
    }
}