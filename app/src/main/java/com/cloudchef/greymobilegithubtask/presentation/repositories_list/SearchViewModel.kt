package com.cloudchef.greymobilegithubtask.presentation.repositories_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cloudchef.greymobilegithubtask.common.Resource
import com.cloudchef.greymobilegithubtask.domain.repository.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel@Inject constructor(
    private val repository: GithubRepository): ViewModel() {

    var state by mutableStateOf(SearchViewState())

    private var searchJob: Job? = null


    fun onEvent(event: SearchEvent) {
        when(event) {
            is SearchEvent.OnSearchQueryChange ->  {
                state = state.copy(searchQuery = event.query)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500L)
                    getRepositoryList()
                }
            }
        }
    }

    private fun getRepositoryList(
        query: String = state.searchQuery.lowercase(),
    ) {
        viewModelScope.launch {
            repository
                .fetchRepo( query)
                .collect { result ->
                    when(result) {
                        is Resource.Success -> {
                            result.data?.let { listings ->
                                state = state.copy(
                                    user = listings
                                )
                            }
                            searchJob?.cancel()
                        }
                        is Resource.Error -> {
                            searchJob?.cancel()
                        }
                        is Resource.Loading -> {
                            state = state.copy(isLoading = result.isLoading)
                        }
                    }
                }
        }
    }

    override fun onCleared() {
        super.onCleared()
        searchJob?.cancel()
    }
}