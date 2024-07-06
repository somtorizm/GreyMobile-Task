package com.cloudchef.greymobilegithubtask.di

import com.cloudchef.greymobilegithubtask.common.Constants
import com.cloudchef.greymobilegithubtask.data.remote.GithubApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModules {

    @Provides
    @Singleton
    fun provideStockApi(): GithubApi {
        val networkJson = Json { ignoreUnknownKeys = true }
        val contentType = "application/json".toMediaType()

        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC })
            .build()

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(networkJson.asConverterFactory(contentType))
            .client(client)
            .build()
            .create(GithubApi::class.java)
    }
}