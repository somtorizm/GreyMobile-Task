package com.cloudchef.greymobilegithubtask.presentation.search_user

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cloudchef.greymobilegithubtask.presentation.search_repository.SearchBar
import com.cloudchef.greymobilegithubtask.presentation.search_repository.SearchEmptyStateView
import com.cloudchef.greymobilegithubtask.presentation.search_repository.SearchEvent
import com.cloudchef.greymobilegithubtask.presentation.user_detail.GithubUserViewModel

@Composable
fun UserScreen(
    navController: NavController,
    viewModel: SearchUserViewModel = hiltViewModel()
) {
    val state = rememberLazyListState()
    val model = viewModel.state

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
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

        SearchBar("Search for users..") {
            viewModel.onEvent(SearchEvent.OnSearchQueryChange(it))
        }

        when {
            model.isLoading -> {
                SearchEmptyStateView("Searching Github for Repositories")
            }

            model.error != null -> {
                SearchEmptyStateView("An error occurred: ${model.error}")
            }

            model.user?.items?.isEmpty() == true -> {
                SearchEmptyStateView("We’ve searched the ends of the earth and we’ve not found this user, please try again")
            }

            model.user?.items == null -> {
                SearchEmptyStateView("Search Github for repositories, issues and pull requests!")
            }

            else -> {
                Spacer(modifier = Modifier.height(40.dp))

                LazyColumn(state = state) {
                    model.user.items.let { repos ->
                        items(repos, key = { it.id }) { repo ->
                            RepoCard(
                                title = repo.login,
                                subtitle = "",
                                url = repo.avatarUrl,
                                stars = 0,
                                language = "",
                                description = "",
                                tags = listOf(repo.type)
                            ) {
                                //navController.navigate()
                            }
                        }
                    }
                }
            }
        }
    }
}