package com.rheza.gcforward

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.rheza.gcforward.model.User
import com.rheza.gcforward.singleton.DataHolder
import com.rheza.gcforward.view.UserListAdapter
import com.rheza.gcforward.viewmodel.SearchUserViewModel
import com.rheza.gcforward.viewmodel.SearchUserViewModelImpl

class MainActivity: AppCompatActivity(), UserListAdapter.OnItemClickListener {

    private lateinit var mRootLayout: ConstraintLayout
    private lateinit var mEditTxtQuery: EditText
    private lateinit var mSearchButton: Button
    private lateinit var mProgressBar: ProgressBar
    private lateinit var mListUser: RecyclerView
    private lateinit var mUserListAdapter: UserListAdapter
    private var mLastSearchQuery: String = ""

    private lateinit var mSearchUserViewModelImpl: SearchUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mRootLayout = findViewById(R.id.root)
        mEditTxtQuery = findViewById(R.id.edit_search)
        mSearchButton = findViewById(R.id.btn_search)
        mSearchButton.setOnClickListener(searchButtonListener)
        mProgressBar = findViewById(R.id.progress_circular)
        mListUser = findViewById(R.id.list_user)

        // Initialize SearchUsersViewModel
        mSearchUserViewModelImpl =
            ViewModelProvider(this).get(SearchUserViewModelImpl::class.java)


        // Listen to the changes of List of user in SearchUserViewModel
        (mSearchUserViewModelImpl as SearchUserViewModelImpl).users.observe(this) {
            mProgressBar.visibility = View.GONE

            // Show error message and return when empty result is returned
            if(it.isEmpty()) {
                Snackbar.make(mRootLayout,
                    getString(R.string.search_error_empty_result),
                    Snackbar.LENGTH_SHORT).show()
                return@observe
            }
            mListUser.visibility = View.VISIBLE

            // Submit the user list to adapter to update the adapter content
            mUserListAdapter.submitList(it)
        }

        mUserListAdapter = UserListAdapter(this)
        mListUser.adapter = mUserListAdapter
    }

    private val searchButtonListener = View.OnClickListener {
        // Prevent search request if the last search is the same query or the query is empty
        if (mEditTxtQuery.text.isEmpty()
            || mEditTxtQuery.text.toString().equals(mLastSearchQuery, ignoreCase = true)) {

            // Show error message
            Snackbar.make(mRootLayout,
                getString(R.string.search_error_query_empty_or_same),
                Snackbar.LENGTH_SHORT).show()
            return@OnClickListener
        }
        mProgressBar.visibility = View.VISIBLE
        mLastSearchQuery = mEditTxtQuery.text.toString()
        mSearchUserViewModelImpl.searchUser(mLastSearchQuery)
    }

    override fun onItemClick(user: User) {
        // Set clicked user object to DataHolder
        val dataHolder: DataHolder = DataHolder.getInstance()
        dataHolder.setUser(user)

        // Run Profile Activity
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()

        // cancel running jobs to prevent running background operation
        mSearchUserViewModelImpl.cancelJobs()
    }
}