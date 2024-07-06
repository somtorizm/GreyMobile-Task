package com.cloudchef.greymobilegithubtask.domain.repository

import com.cloudchef.greymobilegithubtask.domain.model.GithubRepoModel
import com.cloudchef.greymobilegithubtask.domain.model.GithubUserModel

interface GithubRepository  {
    suspend fun fetchUser(username: String): GithubUserModel

    suspend fun fetchRepo(query: String): List<GithubRepoModel>
}