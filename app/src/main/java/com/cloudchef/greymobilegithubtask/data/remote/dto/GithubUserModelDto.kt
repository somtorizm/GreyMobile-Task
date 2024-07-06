package com.cloudchef.greymobilegithubtask.data.remote.dto

import com.cloudchef.greymobilegithubtask.domain.model.GithubUserModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GithubUserModelDto(
    val login: String,
    val id: Int,
    @SerialName("node_id")
    val nodeId: String,

    @SerialName("avatar_url")
    val avatarUrl: String,

    @SerialName("gravatar_id")
    val gravatarId: String? = null,

    val url: String,

    @SerialName("html_url")
    val htmlUrl: String,

    @SerialName("followers_url")
    val followersUrl: String,

    @SerialName("following_url")
    val followingUrl: String,

    @SerialName("gists_url")
    val gistsUrl: String,

    @SerialName("starred_url")
    val starredUrl: String,

    @SerialName("subscriptions_url")
    val subscriptionsUrl: String,

    @SerialName("organizations_url")
    val organizationsUrl: String,

    @SerialName("repos_url")
    val reposUrl: String,
    @SerialName("events_url") val eventsUrl: String,
    @SerialName("received_events_url") val receivedEventsUrl: String,

    val type: String,

    @SerialName("site_admin")
    val siteAdmin: Boolean,

    val name: String? = null,
    val company: String? = null,
    val blog: String? = null,
    val location: String? = null,
    val email: String? = null,
    val hireable: Boolean? = null,
    val bio: String? = null,

    @SerialName("twitter_username")
    val twitterUsername: String? = null,

    @SerialName("public_repos")
    val publicRepos: Int,

    @SerialName("public_gists")
    val publicGists: Int,

    val followers: Int,
    val following: Int,

    @SerialName("created_at")
    val createdAt: String,

    @SerialName("updated_at")
    val updatedAt: String
) {
    companion object {
        fun GithubUserModelDto.toDomain (): GithubUserModel {
            return GithubUserModel(
                login, id, nodeId, avatarUrl, gravatarId, url, htmlUrl, followersUrl,
                followingUrl, gistsUrl, starredUrl, subscriptionsUrl, organizationsUrl,
                reposUrl, eventsUrl, receivedEventsUrl, type, siteAdmin, name, company,
                blog, location, email, hireable, bio, twitterUsername, publicRepos,
                publicGists, followers, following, createdAt, updatedAt
            )
        }
    }
}
