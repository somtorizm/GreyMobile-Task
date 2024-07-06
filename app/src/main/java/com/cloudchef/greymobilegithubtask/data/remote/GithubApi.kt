package com.cloudchef.greymobilegithubtask.data.remote

import com.cloudchef.greymobilegithubtask.data.remote.dto.GithubRepoModelDto
import com.cloudchef.greymobilegithubtask.data.remote.dto.GithubUserModelDto
import retrofit2.http.GET

interface GithubApi {

    @GET("/users/")
    suspend fun fetchUser(username: String): GithubUserModelDto

    @GET("/search/repositories/")
    suspend fun fetchRepo(query: String): List<GithubRepoModelDto>

}