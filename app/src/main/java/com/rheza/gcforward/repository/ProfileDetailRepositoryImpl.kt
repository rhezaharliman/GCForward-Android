package com.rheza.gcforward.repository

import androidx.lifecycle.LiveData
import com.rheza.gcforward.helper.api.RetrofitHelper
import com.rheza.gcforward.model.RepositoryInfo
import com.rheza.gcforward.model.User
import kotlinx.coroutines.*

object ProfileDetailRepositoryImpl: ProfileDetailRepository {
    // Controlling asynchronous works happened in SearchUsersRepositoryImpl
    var jobGetUserDetail: CompletableJob? = null
    var jobGetRepositoryInfo: CompletableJob? = null

    /**
     * Run a job for searching user.
     */
    override fun getUserDetailFromLoginId(loginId: String): LiveData<User> {
        jobGetUserDetail = Job()
        return object: LiveData<User>() {
            override fun onActive() {
                super.onActive()
                // make a unique coroutine IO scope with the completable job
                jobGetUserDetail?.let { CoroutineScope(Dispatchers.IO + it).launch {
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

    /**
     * Run a job for getting repository information
     */
    override fun getReposFromLoginId(loginId: String): LiveData<List<RepositoryInfo>> {
        jobGetRepositoryInfo = Job()
        return object: LiveData<List<RepositoryInfo>>() {
            override fun onActive() {
                super.onActive()
                // make a unique coroutine IO scope with the completable job
                jobGetRepositoryInfo?.let { CoroutineScope(Dispatchers.IO + it).launch {
                    val searchResult = RetrofitHelper.githubApiHelper.getReposFromLoginId(loginId)
                    // TODO: Notify when there's a request error

                    // Return only non-forked repository
                    val repositories: MutableList<RepositoryInfo> = ArrayList()
                    if (searchResult.isSuccessful && searchResult.body()?.isEmpty() == false) {
                        val requestResult: List<RepositoryInfo> = searchResult.body()?: emptyList()
                        for (repository: RepositoryInfo in requestResult) {
                            if (!repository.fork) {
                                repositories.add(repository)
                            }
                        }
                    }

                    // notify main thread when the operation is finished
                    withContext(Dispatchers.Main) {
                        value = repositories
                        it.complete()
                    }
                } }
            }
        }
    }

    override fun cancelJobs() {
        jobGetUserDetail?.cancel()
        jobGetRepositoryInfo?.cancel()
    }
}