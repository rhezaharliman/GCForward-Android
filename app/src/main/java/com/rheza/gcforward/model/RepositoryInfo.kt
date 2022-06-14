package com.rheza.gcforward.model

import com.squareup.moshi.Json

/**
 * Data class to handle response from repository details
 *
 * MEMO:
 * There are some fields that is not necessary for now.
 * Skip implementing it for the time being.
 */
data class RepositoryInfo (
    @field:Json(name = "id")
    val id: String? = null,

    @field:Json(name = "node_id")
    val nodeId: String? = null,

    @field:Json(name = "name")
    val name: String? = null,

    @field:Json(name = "full_name")
    val fullName: String? = null,

    @field:Json(name = "private")
    val private: Boolean = false,

    @field:Json(name = "owner")
    val owner: User? = null,

    @field:Json(name = "html_url")
    val html_url: String? = null,

    @field:Json(name = "description")
    val description: String? = null,

    @field:Json(name = "fork")
    val fork: Boolean = false,

    @field:Json(name = "url")
    val url: String? = null,

    @field:Json(name = "created_at")
    val createdAt: String? = null,

    @field:Json(name = "updated_at")
    val updatedAt: String? = null,

    @field:Json(name = "pushed_at")
    val pushedAt: String? = null,

    @field:Json(name = "homepage")
    val homepage: String? = null,

    @field:Json(name = "size")
    val size: Long = 0,

    @field:Json(name = "stargazers_count")
    val stargazersCount: Long = 0,

    @field:Json(name = "watchers_count")
    val watchersCount: Long = 0,

    @field:Json(name = "language")
    val language: String? = null,

    @field:Json(name = "archived")
    val archived: Boolean = false,

    @field:Json(name = "disabled")
    val disabled: Boolean = false,

    @field:Json(name = "visibility")
    val visibility: String? = null,
)