package com.rheza.gcforward.repository

import androidx.lifecycle.LiveData
import com.rheza.gcforward.helper.api.RetrofitHelper
import com.rheza.gcforward.model.User
import kotlinx.coroutines.*

object ProfileDetailRepositoryImpl: ProfileDetailRepository {
    // Controlling asynchronous works happened in SearchUsersRepositoryImpl
    var job: CompletableJob? = null

    /**
     * Run a job for searching user.
     */
    override fun getUserDetailFromLoginId(loginId: String): LiveData<User> {
        job = Job()
        return object: LiveData<User>() {
            override fun onActive() {
                super.onActive()
                // make a unique coroutine IO scope with the completable job
                job?.let { CoroutineScope(Dispatchers.IO + it).launch {
                    val searchResult =
                        RetrofitHelper.githubApiHelper.getUserDetailFromLoginId(loginId)
                    // TODO: Notify when there's a request error
                    // notify main thread when the operation is finished
                    var result:User? = null
                    if (searchResult.isSuccessful) {
                        result = searchResult.body()
                    }
                    withContext(Dispatchers.Main) {
                        value = result
                        it.complete()
                    }
                } }
            }
        }
    }

    override fun cancelJobs() {
        job?.cancel()
    }
}