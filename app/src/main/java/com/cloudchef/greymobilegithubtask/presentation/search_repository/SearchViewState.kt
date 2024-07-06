package com.cloudchef.greymobilegithubtask.presentation.search_repository

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.cloudchef.greymobilegithubtask.domain.model.GithubRepoModel

@Stable
@Immutable
data class SearchViewState(
    val user: GithubRepoModel? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val searchQuery: String = ""
)
