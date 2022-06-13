package com.rheza.gcforward

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar
import com.rheza.gcforward.model.User

class ProfileActivity : AppCompatActivity() {
    private lateinit var mRootView: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        // Get passed data from MainActivity
        val state = intent.getSerializableExtra(KEY)

        // TODO: Fetch passed data and write it to view
    }

    companion object {
        const val KEY = "PROFILE"
    }
}