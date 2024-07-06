package com.cloudchef.greymobilegithubtask.presentation.search_user

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.cloudchef.greymobilegithubtask.domain.model.GithubUserList

@Stable
@Immutable
data class SearchUserState(
    val user: GithubUserList? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val searchQuery: String = ""
)
