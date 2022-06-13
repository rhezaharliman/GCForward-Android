package com.rheza.gcforward.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rheza.gcforward.model.User

object MockedSearchUserRepository : SearchUsersRepository {
    override fun searchUsers(query: String): LiveData<List<User>> {
        val mockLiveData: MutableLiveData<List<User>> by lazy {
            MutableLiveData<List<User>>()
        }
        val user = User(query,
            0,
            "1",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            0,
            "",
            "",
            "",
            "",
            false)
        val userList: List<User> = listOf(user, user)
        mockLiveData.value = userList
        return mockLiveData
    }

    override fun cancelJobs() {
        // DO NOTHING
    }
}