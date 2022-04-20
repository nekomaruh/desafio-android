package com.example.desafio_android_accenture.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio_android_accenture.R
import com.example.desafio_android_accenture.data.imageloader.ImageLoader
import com.example.desafio_android_accenture.data.model.PullRequestModel
import com.example.desafio_android_accenture.ui.viewholders.PullRequestViewHolder

class PullRequestAdapter(private val manager: AdapterManager) :
    RecyclerView.Adapter<PullRequestViewHolder>() {
    private val pullRequestList = mutableListOf<PullRequestModel>()

    interface AdapterManager {
        fun provideImageLoader(): ImageLoader
    }

    fun addPullRequests(pullRequests: List<PullRequestModel>) {
        val diffUtil = PullRequestItemDiffCallback(pullRequestList, pullRequests)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        // diffResults.dispatchUpdatesTo(this)
        pullRequestList.clear()
        pullRequestList.addAll(pullRequests)
        diffResults.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullRequestViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PullRequestViewHolder(
            layoutInflater.inflate(R.layout.item_pull_request, parent, false), manager
        )
    }

    override fun onBindViewHolder(holder: PullRequestViewHolder, position: Int) {
        val item = pullRequestList[position]
        return holder.render(item)
    }

    override fun getItemCount(): Int = pullRequestList.size

    fun clear() = pullRequestList.clear()

    class PullRequestItemDiffCallback(
        var oldList: List<PullRequestModel>,
        var newList: List<PullRequestModel>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = oldList[oldItemPosition].user
            val newItem = newList[newItemPosition].user
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = oldList[oldItemPosition]
            val newItem = newList[newItemPosition]
            return oldItem.equals(newItem)
        }
    }
}