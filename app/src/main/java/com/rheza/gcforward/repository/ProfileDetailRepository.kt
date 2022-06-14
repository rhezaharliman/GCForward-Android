package com.rheza.gcforward.repository

import androidx.lifecycle.LiveData
import com.rheza.gcforward.model.User

interface ProfileDetailRepository {
    fun getUserDetailFromLoginId(loginId: String): LiveData<User>
    fun cancelJobs()
}