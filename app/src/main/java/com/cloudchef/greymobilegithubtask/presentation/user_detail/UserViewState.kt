package com.cloudchef.greymobilegithubtask.presentation.user_detail

import com.cloudchef.greymobilegithubtask.domain.model.GithubUserModel

data class UserViewState(
    val user: GithubUserModel? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)