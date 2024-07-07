package com.cloudchef.greymobilegithubtask.presentation.user_detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cloudchef.greymobilegithubtask.common.Resource
import com.cloudchef.greymobilegithubtask.domain.repository.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubUserViewModel @Inject constructor(
    private val repository: GithubRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    var state by mutableStateOf(UserViewState())

    init {
        fetchUser()
        fetchUserRepo()
    }

    private fun fetchUser() {
        viewModelScope.launch {
            val id = savedStateHandle.get<String>("userId") ?: return@launch
            state = state.copy(isLoading = true)

            repository.fetchUser(id).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let { user ->
                            state = state.copy(user = user, isLoading = false, error = null)
                        }

                    }
                    is Resource.Error -> {
                        state = state.copy(isLoading = false, error = result.message ?: "Unknown error", usersRepo = null)
                    }
                    is Resource.Loading -> {
                        state = state.copy(isLoading = result.isLoading, error = null)
                    }
                }
            }
        }
    }

    private fun fetchUserRepo() {
        viewModelScope.launch {
            val id = savedStateHandle.get<String>("userId") ?: return@launch

            repository.fetchUserRepo(id).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let { repos ->
                            state = state.copy(usersRepo = repos, isLoading = false, error = null)
                        }

                    }
                    is Resource.Error -> {
                        state = state.copy(isLoading = false, error = result.message ?: "Unknown error", usersRepo = null)
                    }
                    is Resource.Loading -> {
                        state = state.copy(isLoading = result.isLoading, error = null)
                    }
                }
            }
        }
    }
}