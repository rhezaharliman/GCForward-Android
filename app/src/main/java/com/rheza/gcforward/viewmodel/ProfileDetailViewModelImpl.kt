package com.rheza.gcforward.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.rheza.gcforward.model.User
import com.rheza.gcforward.repository.ProfileDetailRepositoryImpl

class ProfileDetailViewModelImpl: ViewModel(), ProfileDetailViewModel {
    private val mLoginId: MutableLiveData<String> = MutableLiveData()

    // Transformations.switchMap observing mQuery.
    // When the value of mQuery is changed, then the scope inside switchMap is called
    val user: LiveData<User> = Transformations.switchMap(mLoginId) {
        /**
         * TODO:
         * * Handle request error is necessary
         * * Rather than always requesting to the Github server,
         *   caching the search result to locally will make it better for same query.
         */
        ProfileDetailRepositoryImpl.getUserDetailFromLoginId(it)
    }

    /**
     * Invoke GitHub API
     */
    override fun getUserDetailFromLoginId(loginId: String) {
        if (mLoginId.value == loginId) {
            return
        }
        mLoginId.value = loginId
    }

    override fun cancelJobs() {
        ProfileDetailRepositoryImpl.cancelJobs()
    }
}