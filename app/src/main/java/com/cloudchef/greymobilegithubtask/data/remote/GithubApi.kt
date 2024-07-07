package com.cloudchef.greymobilegithubtask.data.remote

import com.cloudchef.greymobilegithubtask.data.remote.dto.GithubRepoModelDto
import com.cloudchef.greymobilegithubtask.data.remote.dto.GithubUserListDto
import com.cloudchef.greymobilegithubtask.data.remote.dto.GithubUserModelDto
import com.cloudchef.greymobilegithubtask.data.remote.dto.RepositoryDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {

    @GET("/users/{username}")
    suspend fun fetchUser(@Path("username") username: String): GithubUserModelDto

    @GET("search/repositories")
    suspend fun fetchRepo(@Query("q") query: String): GithubRepoModelDto

    @GET("search/users")
    suspend fun searchUsers(@Query("q") query: String): GithubUserListDto

    @GET("users/{username}/repos")
    suspend fun fetchUserRepos(@Path("username") username: String): List<RepositoryDto>
}