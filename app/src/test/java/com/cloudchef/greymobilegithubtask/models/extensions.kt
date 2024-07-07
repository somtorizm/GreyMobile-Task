package com.cloudchef.greymobilegithubtask.models

import com.cloudchef.greymobilegithubtask.domain.model.GithubUserModel
import com.cloudchef.greymobilegithubtask.domain.model.Owner
import com.cloudchef.greymobilegithubtask.domain.model.Repository

fun createGithubUserModel() = GithubUserModel(
    login = "Somto",
    id = 1,
    nodeId = "1",
    avatarUrl = "https://victor.png",
    url = "",
    followersUrl = "",
    receivedEventsUrl = "",
    reposUrl = "",
    organizationsUrl = "",
    eventsUrl = "",
    subscriptionsUrl = "",
    gistsUrl = "",
    starredUrl = "",
    siteAdmin = false,
    htmlUrl = "",
    type = "",
    publicGists = 20,
    followers = 20,
    following = 45,
    createdAt = "",
    updatedAt = "",
    publicRepos = 3,
    hireable = false,
    email = "",
    location = "",
    blog = "",
    company = "",
    twitterUsername = "",
    gravatarId = "",
    followingUrl = ""
)

fun createUserRepos() = listOf(
    Repository(
        1,
        "Victor",
        "Ezinwa Victor",
        Owner("Somto", 1, "https://Image.png", "organisation"),
        false, "", fork = false, defaultBranch = "",
        url = "", stargazersCount = 20, watchersCount = 60,
        forksCount = 10, openIssuesCount = 20, size = 20,
        pushedAt = "", createdAt = "", updatedAt = "", topics = listOf())
)


