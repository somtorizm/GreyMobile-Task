package com.cloudchef.greymobilegithubtask.data.remote

import retrofit2.http.GET

interface GithubApi {

    @GET("/users/")
    suspend fun fetchUser(username: String)
}