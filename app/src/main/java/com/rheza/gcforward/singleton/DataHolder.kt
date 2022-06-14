package com.rheza.gcforward.singleton

import com.rheza.gcforward.model.User

/**
 * Singleton to hold necessary data
 */
object DataHolder {
    private val mHolder: DataHolder = DataHolder
    private var mUser: User? = null

    fun getUser(): User? {
        return mUser
    }

    fun setUser(user: User) {
        mUser = user
    }

    fun getInstance(): DataHolder {
        return mHolder
    }
}