package com.rheza.gcforward

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.rheza.gcforward.model.RepositoryInfo
import com.rheza.gcforward.model.User
import com.rheza.gcforward.singleton.DataHolder
import com.rheza.gcforward.view.RepositoryListAdapter
import com.rheza.gcforward.viewmodel.ProfileDetailViewModel
import com.rheza.gcforward.viewmodel.ProfileDetailViewModelImpl
import com.squareup.picasso.Picasso

class ProfileActivity : AppCompatActivity(), RepositoryListAdapter.OnItemClickListener {
    private lateinit var mRootLayout: ConstraintLayout
    private lateinit var mUser: User
    private lateinit var mProfileDetailViewModel: ProfileDetailViewModel
    private lateinit var mImgProfile: ImageView
    private lateinit var mTxtName: TextView
    private lateinit var mTxtUsername: TextView
    private lateinit var mTxtBio: TextView
    private lateinit var mTxtFollowingNum: TextView
    private lateinit var mTxtFollowerNum: TextView
    private lateinit var mListRepository: RecyclerView
    private lateinit var mListRepositoryAdapter: RepositoryListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        mRootLayout = findViewById(R.id.profile_root)

        // Take user data from DataHolder singleton
        val dataHolder: DataHolder = DataHolder.getInstance()
        val state = dataHolder.getUser()
        if (state != null) {
            mUser = state
        } else {
            Snackbar.make(mRootLayout, getString(R.string.profile_error_wrong_state), Snackbar.LENGTH_SHORT).show()
            return
        }

        // initialize detail views
        mImgProfile = findViewById(R.id.img_profile_profile)
        mTxtName = findViewById(R.id.txt_profile_name)
        mTxtUsername = findViewById(R.id.txt_profile_username)
        mTxtBio = findViewById(R.id.txt_profile_bio)
        mTxtFollowingNum = findViewById(R.id.txt_profile_following_number)
        mTxtFollowerNum = findViewById(R.id.txt_profile_followers_number)
        mListRepository = findViewById(R.id.list_profile_repository)

        // Initialize ProfileDetailViewModel
        mProfileDetailViewModel =
            ViewModelProvider(this).get(ProfileDetailViewModelImpl::class.java)

        // Listen to the changes of List of user in SearchUserViewModel
        (mProfileDetailViewModel as ProfileDetailViewModelImpl).user.observe(this) {

            // Show error message and return when null result is returned
            if (it == null) {
                Snackbar.make(mRootLayout,
                    getString(R.string.search_error_empty_result),
                    Snackbar.LENGTH_SHORT).show()
                return@observe
            }

            val user: User = it

            // Put all user data to view
            // use Picasso to load image to ImageView
            Picasso.get().load(user.avatarUrl).into(mImgProfile)
            mTxtName.text = user.name
            mTxtUsername.text = user.login
            mTxtBio.text = user.bio
            mTxtFollowingNum.text = user.following.toString()
            mTxtFollowerNum.text = user.followers.toString()
        }

        // Listen to changes in repository info list
        (mProfileDetailViewModel as ProfileDetailViewModelImpl).repositoryInfo.observe(this) {

            // Show error message and return when null result is returned
            if (it.isEmpty()) {
                Snackbar.make(mRootLayout,
                    getString(R.string.search_error_empty_result),
                    Snackbar.LENGTH_SHORT).show()
                return@observe
            }

            // update repository adaptor with the new list
            val repositoryList: List<RepositoryInfo> = it
            mListRepositoryAdapter.submitList(repositoryList)
        }

        // initialize repository adapter and set it to the list repository recycler view
        mListRepositoryAdapter = RepositoryListAdapter(this)
        mListRepository.adapter = mListRepositoryAdapter

        // Fetch user detail.
        // Since mUser is assigned from nullable variable. Therefore safety call is necessary
        mUser.login?.let {
            (mProfileDetailViewModel as ProfileDetailViewModelImpl).getUserDetailFromLoginId(
                it
            )
        }
    }

    // item click when the repository list is clicked
    override fun onItemClick(repository: RepositoryInfo) {
        Snackbar.make(mRootLayout, "${repository.name} CLICKED", Snackbar.LENGTH_SHORT).show()
        // TODO: implement CustomTab
    }
}