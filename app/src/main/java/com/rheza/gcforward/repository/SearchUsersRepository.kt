package com.rheza.gcforward.repository

import androidx.lifecycle.LiveData
import com.rheza.gcforward.model.User

/**
 * Repository interface for Search Users
 */
interface SearchUsersRepository {
    fun searchUsers(query: String): LiveData<List<User>>
    fun cancelJobs()
}