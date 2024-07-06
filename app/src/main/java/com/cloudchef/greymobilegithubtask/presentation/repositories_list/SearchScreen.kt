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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cloudchef.greymobilegithubtask.R
import com.cloudchef.greymobilegithubtask.presentation.user_detail.RepoCard

@Composable
fun SearchScreen() {
    SearchScreenView(
        modifier = Modifier
        .padding(horizontal = 20.dp))
}

@Composable
fun SearchScreenView(
    modifier: Modifier,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val state = rememberLazyListState()
    val model = viewModel.state

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

        SearchBar {
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
                    model.user?.items?.let { repos ->
                        items(repos, key = { it.id }) { repo ->
                            RepoCard(
                                title = repo.name,
                                subtitle = repo.owner.login,
                                url = repo.owner.avatarUrl,
                                stars = repo.stargazersCount,
                                language = repo.language ?: "",
                                description = repo.description ?: "",
                                tags = repo.topics.take(4)
                            )
                        }
                    }
                }
            }
        }

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
                textAlign = TextAlign.Center,
            )
        }
    }
}