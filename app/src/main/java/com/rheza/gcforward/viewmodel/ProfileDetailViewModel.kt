package com.rheza.gcforward.viewmodel

/**
 * ViewModel class for Profile detail
 */
interface ProfileDetailViewModel {
    /**
     * Pass login id to get user detail
     */
    fun getUserDetailFromLoginId(loginId: String)

    /**
     * Cancel all running jobs.
     * Execute this onDestroy to prevent running jobs in the background
     */
    fun cancelJobs()
}