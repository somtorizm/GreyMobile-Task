package com.cloudchef.greymobilegithubtask.presentation.home

import com.cloudchef.greymobilegithubtask.R

sealed class ScreenNav (
    val id: String,
    val title: String,
    val icon: Int
) {
    object Home: ScreenNav("Home", "Home", R.drawable.user)
    object Search: ScreenNav("Search", "Search", R.drawable.user)
    object Users: ScreenNav("User", "User", R.drawable.user)

    object Items {
        val list = listOf(Home, Search, Users)
    }
}