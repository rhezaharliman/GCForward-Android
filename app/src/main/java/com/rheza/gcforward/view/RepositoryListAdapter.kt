package com.rheza.gcforward.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rheza.gcforward.R
import com.rheza.gcforward.model.RepositoryInfo

/**
 * Adapter for Repository List
 */
class RepositoryListAdapter(itemClickListener: OnItemClickListener):
    ListAdapter<RepositoryInfo, RecyclerView.ViewHolder>(DiffCallback())
    {

    private val mItemClickListener: OnItemClickListener = itemClickListener

    // Initialize the ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.repository_item, parent, false)
        return ViewHolder(view)
    }

    // Set the view value with user data
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // Capture the view from ViewHolder class
        holder as ViewHolder
        val repository = getItem(position)

        // set repository name to TextView
        holder.txtName.text = repository.name

        // set description
        holder.txtDesc.text = repository.description

        // FIXME: Use language_url gives more coverage in languages that used in the project
        // set language
        holder.txtLanguage.text = repository.language

        // set stars
        val stars = Char(0x2B50) + " " + repository.stargazersCount.toString()
        holder.txtStar.text = stars

        // set listener to ViewHolder
        holder.itemView.setOnClickListener { mItemClickListener.onItemClick(repository) }
    }

    /**
     * Create Item click listener interface
     */
    interface OnItemClickListener {
        fun onItemClick(repository: RepositoryInfo)
    }

    /**
     * Create ViewHolder class that consist of item in repository item layout
     */
    private class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtName: TextView
        val txtDesc: TextView
        val txtLanguage: TextView
        val txtStar: TextView

        init {
            txtName = view.findViewById(R.id.txt_repository_name)
            txtDesc = view.findViewById(R.id.txt_repository_desc)
            txtLanguage = view.findViewById(R.id.txt_repository_lang)
            txtStar = view.findViewById(R.id.txt_repository_stars)
        }
    }

    /**
     * Compare the items in the list. ListAdapter does not need to be updated if it is not necessary
     */
    private class DiffCallback : DiffUtil.ItemCallback<RepositoryInfo>() {

        override fun areItemsTheSame(oldItem: RepositoryInfo, newItem: RepositoryInfo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: RepositoryInfo, newItem: RepositoryInfo): Boolean {
            return oldItem == newItem
        }
    }
}