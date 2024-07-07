package com.cloudchef.greymobilegithubtask.domain.model

data class GithubUserList(
    val totalCount: Int,
    val incompleteResults: Boolean,
    val items: List<Owner>
)
