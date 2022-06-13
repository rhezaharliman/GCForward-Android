package com.rheza.gcforward

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar
import com.rheza.gcforward.model.User
import com.rheza.gcforward.singleton.DataHolder

class ProfileActivity : AppCompatActivity() {
    private lateinit var mRootView: ConstraintLayout
    private lateinit var mUser: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        mRootView = findViewById(R.id.profile_root)

        val dataHolder: DataHolder = DataHolder.getInstance()
        val state = dataHolder.getUser()
        if (state != null) {
            mUser = state
        } else {
            Snackbar.make(mRootView, getString(R.string.search_error_query_empty_or_same), Snackbar.LENGTH_SHORT).show()
        }
    }

    companion object {
        const val KEY = "PROFILE"
    }
}