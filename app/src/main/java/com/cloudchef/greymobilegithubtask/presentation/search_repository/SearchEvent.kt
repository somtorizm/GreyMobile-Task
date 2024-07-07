package com.cloudchef.greymobilegithubtask.presentation.search_repository

sealed class SearchEvent {
    data class OnSearchQueryChange(val query: String): SearchEvent()
}