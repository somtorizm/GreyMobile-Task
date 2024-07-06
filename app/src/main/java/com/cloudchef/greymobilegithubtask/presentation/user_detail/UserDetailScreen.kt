package com.cloudchef.greymobilegithubtask.presentation.user_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun UserScreen(
    modifier: Modifier,
    navController: NavController,
    viewModel: GithubUserViewModel = hiltViewModel()
) {
    UserScreenView(modifier = modifier
        .padding(horizontal = 20.dp), navController)
}

@Composable
fun UserScreenView(modifier: Modifier, navController: NavController) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 40.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Users",
            fontSize = 18.sp,
            modifier = Modifier
        )

        Spacer(modifier = Modifier.height(40.dp))
    }
}