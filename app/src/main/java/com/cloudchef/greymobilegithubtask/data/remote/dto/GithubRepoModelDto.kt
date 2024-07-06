package com.cloudchef.greymobilegithubtask.data.remote.dto

import com.cloudchef.greymobilegithubtask.data.remote.dto.GithubUserModelDto.Companion.toDomain
import com.cloudchef.greymobilegithubtask.data.remote.dto.RepositoryDto.Companion.toDomain
import com.cloudchef.greymobilegithubtask.domain.model.GithubRepoModel
import com.cloudchef.greymobilegithubtask.domain.model.Repository
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class GithubRepoModelDto(
    @SerialName("total_count")
    val totalCount: Int,

    @SerialName("incomplete_results")
    val incompleteResults: Boolean,
    val items: List<RepositoryDto>
) {
    fun GithubRepoModelDto.toDomain() : GithubRepoModel {
        return GithubRepoModel(
            totalCount, incompleteResults, items.map { it.toDomain() }
        )
    }
}

@Serializable
data class RepositoryDto(
    val id: Int,
    val name: String,

    @SerialName("full_name")
    val fullName: String,
    val owner: GithubUserModelDto,
    val private: Boolean,

    @SerialName("html_url")
    val htmlUrl: String,

    val description: String? = null,
    val fork: Boolean,
    val url: String,

    @SerialName("created_at")
    val createdAt: String,

    @SerialName("updated_at")
    val updatedAt: String,

    @SerialName("pushed_at")
    val pushedAt: String,

    val homepage: String? = null,
    val size: Int,

    @SerialName("stargazers_count")
    val stargazersCount: Int,

    @SerialName("watchers_count")
    val watchersCount: Int,

    val language: String? = null,

    @SerialName("forks_count")
    val forksCount: Int,

    @SerialName("open_issues_count")
    val openIssuesCount: Int,

    @SerialName("master_branch")
    val masterBranch: String? = null,

    @SerialName("default_branch")
    val defaultBranch: String,
    val score: Float
) {
    companion object {
        fun RepositoryDto.toDomain() : Repository {
            return Repository(id, name, fullName, owner.toDomain(),
                private, htmlUrl, description, fork, url, createdAt,
                updatedAt, pushedAt, homepage, size, stargazersCount,
                watchersCount, language, forksCount, openIssuesCount,
                masterBranch, defaultBranch, score )
        }
    }
}

