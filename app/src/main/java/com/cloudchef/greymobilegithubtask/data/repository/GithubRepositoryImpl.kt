package com.cloudchef.greymobilegithubtask.data.repository

import com.cloudchef.greymobilegithubtask.data.remote.GithubApi
import com.cloudchef.greymobilegithubtask.data.remote.dto.GithubRepoModelDto.Companion.toDomain
import com.cloudchef.greymobilegithubtask.data.remote.dto.GithubUserModelDto.Companion.toDomain
import com.cloudchef.greymobilegithubtask.domain.model.GithubRepoModel
import com.cloudchef.greymobilegithubtask.domain.model.GithubUserModel
import com.cloudchef.greymobilegithubtask.domain.repository.GithubRepository
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private val githubApi: GithubApi
) : GithubRepository{
    override suspend fun fetchUser(username: String): GithubUserModel {
        return githubApi.fetchUser(username).toDomain()
    }

    override suspend fun fetchRepo(query: String): List<GithubRepoModel> {
        return githubApi.fetchRepo(query).map { it.toDomain() }
    }
}