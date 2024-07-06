package com.cloudchef.greymobilegithubtask.presentation.repositories_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cloudchef.greymobilegithubtask.R
import com.cloudchef.greymobilegithubtask.presentation.user_detail.GithubUserViewModel

@Composable
fun SearchScreen(
    modifier: Modifier,
    navController: NavController,
    viewModel: GithubUserViewModel = hiltViewModel()
) {
    SearchScreenView(modifier = modifier
        .padding(horizontal = 20.dp), navController)
}

@Composable
fun SearchScreenView(modifier: Modifier, navController: NavController) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 40.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Repositories",
            fontSize = 18.sp,
            modifier = Modifier
        )

        Spacer(modifier = Modifier.height(40.dp))

        SearchBar()

        Spacer(modifier = Modifier.height(40.dp))

        SearchEmptyStateView("Search Github for users")
    }
}

@Composable
fun SearchEmptyStateView(message: String) {
    Box(modifier = Modifier
        .fillMaxSize()) {
        Column (
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center){

            Icon(
                painter = painterResource(id = R.drawable.search),
                contentDescription = "No result found,",
                modifier = Modifier
                    .fillMaxHeight(0.4f)
                    .fillMaxWidth())

            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = message,
                fontSize = 18.sp,
                modifier = Modifier
            )
        }
    }
}