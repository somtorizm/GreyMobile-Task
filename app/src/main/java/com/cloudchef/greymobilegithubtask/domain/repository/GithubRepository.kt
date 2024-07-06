package com.cloudchef.greymobilegithubtask.domain.repository

import com.cloudchef.greymobilegithubtask.common.Resource
import com.cloudchef.greymobilegithubtask.domain.model.GithubRepoModel
import com.cloudchef.greymobilegithubtask.domain.model.GithubUserModel
import kotlinx.coroutines.flow.Flow

interface GithubRepository  {
    suspend fun fetchUser(username: String): Resource<GithubUserModel>

    suspend fun fetchRepo(query: String): Flow<Resource<List<GithubRepoModel?>>>
}