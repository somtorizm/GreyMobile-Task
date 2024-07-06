package com.cloudchef.greymobilegithubtask.presentation.repositories_list

sealed class SearchEvent {
    data class OnSearchQueryChange(val query: String): SearchEvent()
}