package com.rheza.gcforward.helper.api

import com.rheza.gcforward.model.SearchResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * GithubAPIHelper consists all request function to GitHub API
 */
interface GithubApiHelper {

    /**
     * Request to api.github.com/search/users to get list of users from certain query
     */
    @GET("search/users")
    suspend fun searchUsers(
        @Query("q") query: String
    ): Response<SearchResult>
}