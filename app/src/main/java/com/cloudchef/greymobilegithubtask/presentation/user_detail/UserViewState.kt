package com.cloudchef.greymobilegithubtask.presentation.user_detail

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.cloudchef.greymobilegithubtask.domain.model.GithubUserModel
import com.cloudchef.greymobilegithubtask.domain.model.Repository

@Stable
@Immutable
data class UserViewState(
    val user: GithubUserModel? = null,
    val usersRepo: List<Repository>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)