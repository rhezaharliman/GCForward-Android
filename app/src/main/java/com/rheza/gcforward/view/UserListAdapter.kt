package com.rheza.gcforward.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rheza.gcforward.R
import com.rheza.gcforward.model.User
import com.squareup.picasso.Picasso

/**
 * Adapter for List user fragment
 */
class UserListAdapter(itemClickListener: OnItemClickListener):
    ListAdapter<User, RecyclerView.ViewHolder>(DiffCallback()) {

    private val mItemClickListener: OnItemClickListener = itemClickListener

    // Initialize the ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return ViewHolder(view)
    }

    // Set the view value with user data
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // Capture the view from ViewHolder class
        holder as ViewHolder
        val user = getItem(position)

        // use Picasso to load image to ImageView
        Picasso.get().load(user.avatarUrl).into(holder.imgProfile)

        // set user name to TextView
        holder.txtUser.text = user.login

        // set listener to ViewHolder
        holder.itemView.setOnClickListener { mItemClickListener.onItemClick(user) }
    }

    /**
     * Create Item click listener interface
     */
    interface OnItemClickListener {
        fun onItemClick(user: User)
    }

    /**
     * Create ViewHolder class that consist of item in user item layout
     */
    private class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val imgProfile: ImageView
        val txtUser: TextView
        init {
            imgProfile = view.findViewById(R.id.img_profile)
            txtUser = view.findViewById(R.id.txt_username)
        }
    }

    /**
     * Compare the items in the list. ListAdapter does not need to be updated if it is not necessary
     */
    private class DiffCallback : DiffUtil.ItemCallback<User>() {

        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }
}