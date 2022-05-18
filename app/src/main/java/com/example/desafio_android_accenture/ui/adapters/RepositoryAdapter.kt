package com.example.desafio_android_accenture.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.desafio_android_accenture.R
import com.example.desafio_android_accenture.data.imageloader.ImageLoader
import com.example.desafio_android_accenture.presentation.model.RepositoryItem
import com.example.desafio_android_accenture.ui.viewholders.RepositoryViewHolder

class RepositoryAdapter(private val manager: AdapterManager) : BaseAdapter<RepositoryItem>() {

    interface AdapterManager {
        fun onRepositoryClick(item: RepositoryItem)
        fun provideImageLoader(): ImageLoader
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_repository, parent, false)
        return RepositoryViewHolder(view, manager)
    }

    /*
    class RepoItemDiffCallback(
        var oldList: List<RepositoryItem>,
        var newList: List<RepositoryItem>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = oldList[oldItemPosition].fullName
            val newItem = newList[newItemPosition].fullName
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