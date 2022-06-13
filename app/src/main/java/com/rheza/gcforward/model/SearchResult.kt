package com.rheza.gcforward.model

import com.squareup.moshi.Json

/**
 * SearchResult to handle response from GitHub API
 */
data class SearchResult (
    @field:Json(name = "total_count")
    val totalCount: Long = 0,

    @field:Json(name = "incomplete_results")
    val incompleteResult: Boolean = false,

    @field:Json(name = "items")
    val items: List<User>? = null,

    @field:Json(name = "message")
    val message: String? = null,

    @field:Json(name = "documentation_url")
    val documentationUrl: String? = null
)