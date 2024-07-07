package com.cloudchef.greymobilegithubtask.presentation.search_user

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cloudchef.greymobilegithubtask.common.Resource
import com.cloudchef.greymobilegithubtask.domain.repository.GithubRepository
import com.cloudchef.greymobilegithubtask.presentation.search_repository.SearchEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchUserViewModel @Inject constructor(
    private val repository: GithubRepository
) : ViewModel() {

    var state by mutableStateOf(SearchUserState())
    var searchJob: Job? = null

    fun onEvent(event: SearchEvent) {
        when(event) {
            is SearchEvent.OnSearchQueryChange ->  {
                state = state.copy(searchQuery = event.query)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    getRepositoryList()
                }
            }
        }
    }

    private fun getRepositoryList(
        query: String = state.searchQuery.lowercase(),
    ) {
        viewModelScope.launch {
            if (query.isBlank()) return@launch
            repository
                .searchUser( query)
                .collect { result ->
                    when(result) {
                        is Resource.Success -> {
                            result.data?.let { userRepos ->
                                state = state.copy(user = userRepos, isLoading = false, error = null)
                            }
                            searchJob?.cancel()
                        }
                        is Resource.Error -> {
                            searchJob?.cancel()
                            state = state.copy(isLoading = false, error = result.message, user = null)
                        }
                        is Resource.Loading -> {
                            state = state.copy(isLoading = result.isLoading)
                        }
                    }
                }
        }
    }

    public override fun onCleared() {
        super.onCleared()
        searchJob?.cancel()
    }
}