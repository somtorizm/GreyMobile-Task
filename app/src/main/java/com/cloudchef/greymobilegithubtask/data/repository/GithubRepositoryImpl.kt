package com.cloudchef.greymobilegithubtask.data.repository

import com.cloudchef.greymobilegithubtask.common.Resource
import com.cloudchef.greymobilegithubtask.data.remote.GithubApi
import com.cloudchef.greymobilegithubtask.data.remote.dto.GithubRepoModelDto.Companion.toDomain
import com.cloudchef.greymobilegithubtask.data.remote.dto.GithubUserModelDto.Companion.toDomain
import com.cloudchef.greymobilegithubtask.domain.model.GithubRepoModel
import com.cloudchef.greymobilegithubtask.domain.model.GithubUserModel
import com.cloudchef.greymobilegithubtask.domain.repository.GithubRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private val githubApi: GithubApi
) : GithubRepository{
    override suspend fun fetchUser(username: String): Resource<GithubUserModel> {
        return try {
            val response = githubApi.fetchUser(username)
            Resource.Success(response.toDomain())
        } catch(e: IOException) {
            e.printStackTrace()
            Resource.Error(message = "Couldn't load user detail: ${e.localizedMessage}")
        } catch(e: HttpException) {
            e.printStackTrace()
            Resource.Error(message = "Couldn't load user info: ${e.localizedMessage}")
        }
    }

    override suspend fun fetchRepo(query: String): Flow<Resource<GithubRepoModel?>> {
        return flow {
            emit(Resource.Loading(true))

            val remoteRepoLists = try {
                val response = githubApi.fetchRepo(query)
                response.toDomain()

            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            } catch (e: Exception) {
                println("Error: ${e.localizedMessage}")
                emit(Resource.Error("Couldn't load data ${e.message}"))
                null
            }
            emit(Resource.Success(remoteRepoLists))
            emit(Resource.Loading(false))
        }
    }
}