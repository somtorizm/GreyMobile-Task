package com.cloudchef.greymobilegithubtask.domain.model

data class GithubRepoModel(
    val totalCount: Int,
    val incompleteResults: Boolean,
    val items: List<Repository>
)

data class Repository(
    val id: Int,
    val name: String,
    val fullName: String,
    val owner: Owner,
    val private: Boolean,
    val htmlUrl: String,
    val description: String? = null,
    val fork: Boolean,
    val url: String,
    val createdAt: String,
    val updatedAt: String,
    val pushedAt: String,
    val homepage: String? = null,
    val size: Int,
    val topics: List<String>,
    val stargazersCount: Int,
    val watchersCount: Int,
    val language: String? = null,
    val forksCount: Int,
    val openIssuesCount: Int,
    val masterBranch: String? = null,
    val defaultBranch: String,
)

data class Owner(
    val login: String,
    val id: Int,
    val avatarUrl: String,
    val type: String,
    )