package com.cloudchef.greymobilegithubtask.presentation

import com.cloudchef.greymobilegithubtask.R

sealed class ScreenNav (
    val id: String,
    val title: String,
    val icon: Int,
    val unselectedIcon: Int
) {
    data object Home: ScreenNav("home", "Home", R.drawable.home, R.drawable.home)
    data object Search: ScreenNav("search", "Search", R.drawable.search_selected, R.drawable.search)
    data object Users: ScreenNav("user", "User", R.drawable.user_selected, R.drawable.user)

    object Items {
        val list = listOf(Home, Search, Users)
    }
}