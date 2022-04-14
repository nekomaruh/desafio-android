package com.example.desafio_android_accenture.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio_android_accenture.R
import com.example.desafio_android_accenture.data.model.PullRequestModel
import com.example.desafio_android_accenture.ui.viewholders.PullRequestViewHolder


class PullRequestAdapter : RecyclerView.Adapter<PullRequestViewHolder>() {
    private val pullRequestList = mutableListOf<PullRequestModel>()

    fun addPullRequests(pullRequests: List<PullRequestModel>) {
        pullRequestList.clear()
        val oldList = pullRequestList
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(
            PullRequestItemDiffCallback(oldList, pullRequests)
        )
        pullRequestList.addAll(pullRequests)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullRequestViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PullRequestViewHolder(
            layoutInflater.inflate(
                R.layout.item_pull_request,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PullRequestViewHolder, position: Int) {
        val item = pullRequestList[position]
        return holder.render(item)
    }

    override fun getItemCount(): Int = pullRequestList.size

    class PullRequestItemDiffCallback(
        var oldList: List<PullRequestModel>,
        var newList: List<PullRequestModel>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].user == newList[oldItemPosition].user
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].equals(newList[newItemPosition])
        }
    }
}