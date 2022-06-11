package com.rheza.gcforward

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar

class MainActivity : AppCompatActivity() {

    lateinit var mSearchButton: Button
    lateinit var mProgressBar:ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mSearchButton = findViewById(R.id.btn_search)
        mProgressBar = findViewById(R.id.progress_circular)
        mSearchButton.setOnClickListener(searchButtonListener)
    }

    val searchButtonListener = View.OnClickListener {
        // TODO: if search operation is on the run, deactivate the button
        mProgressBar.visibility = View.VISIBLE
    }
}