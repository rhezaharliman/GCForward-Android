package com.rheza.gcforward.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.rheza.gcforward.model.User
import com.rheza.gcforward.repository.SearchUsersRepositoryImpl

class SearchUserViewModelImpl: ViewModel(), SearchUserViewModel {
    private val mQuery: MutableLiveData<String> = MutableLiveData()

    // Transformations.switchMap observing mQuery.
    // When the value of mQuery is changed, then the scope inside switchMap is called
    val users: LiveData<List<User>> = Transformations.switchMap(mQuery) {
        /**
         * TODO:
         * * Handle request error is necessary
         * * Rather than always requesting to the Github server,
         *   caching the search result to locally will make it better for same query.
        */
        SearchUsersRepositoryImpl.searchUsers(it)
    }

    /**
     * Invoke GitHub API and
     */
    override fun searchUser(query: String) {
        if (mQuery.value == query) {
            return
        }
        mQuery.value = query
    }

    override fun cancelJobs() {
        SearchUsersRepositoryImpl.cancelJobs()
    }
}