package com.cloudchef.greymobilegithubtask.data.repository

import com.cloudchef.greymobilegithubtask.common.Resource
import com.cloudchef.greymobilegithubtask.data.remote.GithubApi
import com.cloudchef.greymobilegithubtask.data.remote.dto.GithubRepoModelDto.Companion.toDomain
import com.cloudchef.greymobilegithubtask.data.remote.dto.GithubUserListDto.Companion.toDomain
import com.cloudchef.greymobilegithubtask.data.remote.dto.GithubUserModelDto.Companion.toDomain
import com.cloudchef.greymobilegithubtask.data.remote.dto.RepositoryDto.Companion.toDomain
import com.cloudchef.greymobilegithubtask.domain.model.GithubRepoModel
import com.cloudchef.greymobilegithubtask.domain.model.GithubUserList
import com.cloudchef.greymobilegithubtask.domain.model.GithubUserModel
import com.cloudchef.greymobilegithubtask.domain.model.Repository
import com.cloudchef.greymobilegithubtask.domain.repository.GithubRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private val githubApi: GithubApi
) : GithubRepository{
    override suspend fun fetchUser(username: String): Flow<Resource<GithubUserModel>> {
        return flow {
            emit(Resource.Loading(true))

            val user = try {
                val response = githubApi.fetchUser(username)
                response.toDomain()

            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load, Network error: ${e.localizedMessage}"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load, Network error: ${e.localizedMessage}"))
                null
            } catch (e: Exception) {
                println("Error: ${e.localizedMessage}")
                emit(Resource.Error("Couldn't load, error: ${e.localizedMessage}"))
                null
            }
            emit(Resource.Success(user))
            emit(Resource.Loading(false))
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
                emit(Resource.Error("Couldn't load, Network error: ${e.localizedMessage}"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load, Network error: ${e.localizedMessage}"))
                null
            } catch (e: Exception) {
                println("Error: ${e.localizedMessage}")
                emit(Resource.Error("Couldn't load, error: ${e.localizedMessage}"))
                null
            }
            emit(Resource.Success(remoteRepoLists))
            emit(Resource.Loading(false))
        }
    }

    override suspend fun searchUser(query: String): Flow<Resource<GithubUserList?>> {
        return flow {
            emit(Resource.Loading(true))

            val remoteRepoLists = try {
                val response = githubApi.searchUsers(query)
                response.toDomain()

            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load, Network error: ${e.localizedMessage}"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data, Error ${e.localizedMessage}"))
                null
            } catch (e: Exception) {
                println("Error: ${e.localizedMessage}")
                emit(Resource.Error("Couldn't load data ${e.localizedMessage}"))
                null
            }
            emit(Resource.Success(remoteRepoLists))
            emit(Resource.Loading(false))
        }
    }

    override suspend fun fetchUserRepo(query: String): Flow<Resource<List<Repository>>> {
        return flow {
            emit(Resource.Loading(true))

            val remoteUserRepos = try {
                val response = githubApi.fetchUserRepos(query)
                response.map { it.toDomain() }

            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load, Network error: ${e.localizedMessage}"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data, Network Error ${e.localizedMessage}"))
                null
            } catch (e: Exception) {
                println("Error: ${e.localizedMessage}")
                emit(Resource.Error("Couldn't load data ${e.localizedMessage}"))
                null
            }
            emit(Resource.Success(remoteUserRepos))
            emit(Resource.Loading(false))
        }
    }
}