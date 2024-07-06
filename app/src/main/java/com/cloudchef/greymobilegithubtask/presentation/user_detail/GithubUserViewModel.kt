package com.cloudchef.greymobilegithubtask.presentation.user_detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.cloudchef.greymobilegithubtask.domain.repository.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GithubUserViewModel @Inject constructor(
    private val repository: GithubRepository
): ViewModel() {

    var state by mutableStateOf(UserViewState())

    init {

    }
}