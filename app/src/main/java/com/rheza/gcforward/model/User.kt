package com.rheza.gcforward.model

import com.squareup.moshi.Json

/**
 * User object to handle user json data from GitHub response
 */
data class User (
    @field:Json(name = "login")
    val login: String? = null,

    @field:Json(name = "id")
    val id: Long  = 0,

    @field:Json(name = "node_id")
    val nodeId: String? = null,

    @field:Json(name = "avatar_url")
    val avatarUrl: String? = null,

    @field:Json(name = "gravatar_id")
    val gravatarId: String? = null,

    @field:Json(name = "url")
    val url: String? = null,

    @field:Json(name = "html_url")
    val htmlUrl: String? = null,

    @field:Json(name = "followers_url")
    val followersUrl: String? = null,

    @field:Json(name = "subscriptions_url")
    val subscriptionsUrl: String? = null,

    @field:Json(name = "organizations_url")
    val organizationsUrl: String? = null,

    @field:Json(name = "repos_url")
    val reposUrl: String? = null,

    @field:Json(name = "received_events_url")
    val receivedEventsUrl: String? = null,

    @field:Json(name = "type")
    val type: String? = null,

    @field:Json(name = "score")
    val score: Long = 0,

    @field:Json(name = "following_url")
    val followingUrl: String? = null,

    @field:Json(name = "gists_url")
    val gistsUrl: String? = null,

    @field:Json(name = "starred_url")
    val starredUrl: String? = null,

    @field:Json(name = "events_url")
    val eventsUrl: String? = null,

    @field:Json(name = "site_admin")
    val siteAdmin: Boolean = false,

    /* Below is only available from accessing profile url */
    @field:Json(name = "name")
    val name: String? = null,

    @field:Json(name = "company")
    val company: String? = null,

    @field:Json(name = "blog")
    val blog: String? = null,

    @field:Json(name = "location")
    val location: String? = null,

    @field:Json(name = "email")
    val email: String? = null,

    @field:Json(name = "hireable")
    val hireable: String? = null,

    @field:Json(name = "bio")
    val bio: String? = null,

    @field:Json(name = "twitter_username")
    val twitterUsername: String? = null,

    @field:Json(name = "public_repos")
    val publicRepos: String? = null,

    @field:Json(name = "public_gists")
    val publicGists: String? = null,

    @field:Json(name = "followers")
    val followers: Long = 0,

    @field:Json(name = "following")
    val following: Long = 0,

    @field:Json(name = "created_at")
    val createdAt: String? = null,

    @field:Json(name = "updated_at")
    val updatedAt: String? = null
)