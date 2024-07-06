package com.cloudchef.greymobilegithubtask.data.remote

import com.cloudchef.greymobilegithubtask.data.remote.dto.GithubRepoModelDto
import com.cloudchef.greymobilegithubtask.data.remote.dto.GithubUserModelDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {

    @GET("/users/{username}")
    suspend fun fetchUser(@Path("username") username: String): GithubUserModelDto

    @GET("search/repositories")
    suspend fun fetchRepo(@Query("q") query: String): GithubRepoModelDto

}