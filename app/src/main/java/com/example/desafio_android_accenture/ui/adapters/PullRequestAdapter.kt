package com.example.desafio_android_accenture.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.desafio_android_accenture.R
import com.example.desafio_android_accenture.data.imageloader.ImageLoader
import com.example.desafio_android_accenture.presentation.model.PullRequestItem
import com.example.desafio_android_accenture.ui.viewholders.PullRequestViewHolder

class PullRequestAdapter(private val manager: AdapterManager) : BaseAdapter<PullRequestItem>() {

    interface AdapterManager {
        fun provideImageLoader(): ImageLoader
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int, ): PullRequestViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_pull_request, parent, false)
        return PullRequestViewHolder(view, manager)
    }

    /*
    class PullRequestItemDiffCallback(
        var oldList: List<PullRequestItem>,
        var newList: List<PullRequestItem>
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
     */
}