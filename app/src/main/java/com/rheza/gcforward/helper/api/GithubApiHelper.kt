package com.rheza.gcforward.helper.api

import com.rheza.gcforward.model.SearchResult
import com.rheza.gcforward.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
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

    /**
     * Request to api.github/users/{login} to get more detail data
     */
    @GET("users/{login}")
    suspend fun getUserDetailFromLoginId(
        @Path("login") loginId: String
    ): Response<User>
}