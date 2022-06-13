package com.rheza.gcforward.repository

import androidx.lifecycle.LiveData
import com.rheza.gcforward.helper.api.RetrofitHelper
import com.rheza.gcforward.model.User
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object SearchUsersRepositoryImpl : SearchUsersRepository {

    // Controlling asynchronous works happened in SearchUsersRepositoryImpl
    var job: CompletableJob? = null

    /**
     * Run a job for searching user.
     */
    override fun searchUsers(query: String): LiveData<List<User>> {
        job = Job()
        return object: LiveData<List<User>>() {
            override fun onActive() {
                super.onActive()
                // make a unique coroutine IO scope with the completable job
                job?.let { CoroutineScope(IO + it).launch {
                    val searchResult = RetrofitHelper.githubApiHelper.searchUsers(query)
                    // TODO: Notify when there's a request error
                    // notify main thread when the operation is finished
                    var result:List<User>? = emptyList()
                    if (searchResult.isSuccessful) {
                        result = searchResult.body()?.items
                    }
                    withContext(Main) {
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