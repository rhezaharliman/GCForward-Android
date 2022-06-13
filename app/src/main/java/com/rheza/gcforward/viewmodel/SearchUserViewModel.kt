package com.rheza.gcforward.viewmodel

/**
 * ViewModel for search user.
 */
interface SearchUserViewModel {
    /**
     * Pass query as parameter to request
     */
    fun searchUser(query: String)

    /**
     * Cancel all running jobs.
     * Execute this onDestroy to prevent running jobs in the background
     */
    fun cancelJobs()
}