package com.cloudchef.greymobilegithubtask.presentation.user_detail

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.cloudchef.greymobilegithubtask.domain.model.GithubUserModel

@Stable
@Immutable
data class UserViewState(
    val user: GithubUserModel? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)