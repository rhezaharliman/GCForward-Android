package com.rheza.gcforward.viewmodel

import androidx.lifecycle.MutableLiveData
import com.rheza.gcforward.repository.MockedSearchUserRepository

class MockedSearchUserViewModel : SearchUserViewModel {

    private val mQuery: MutableLiveData<String> = MutableLiveData()

    override fun searchUser(query: String) {
    }

    override fun cancelJobs() {
        // DO NOTHING
    }
}