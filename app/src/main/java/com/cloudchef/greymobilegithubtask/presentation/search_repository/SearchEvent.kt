package com.cloudchef.greymobilegithubtask.presentation.search_repository

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

@Stable
@Immutable
sealed class SearchEvent {
    data class OnSearchQueryChange(val query: String): SearchEvent()
}