package com.example.desafio_android_accenture.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio_android_accenture.R
import com.example.desafio_android_accenture.data.imageloader.ImageLoader
import com.example.desafio_android_accenture.data.imageloader.ImageLoaderService
import com.example.desafio_android_accenture.data.model.RepositoryModel
import com.example.desafio_android_accenture.presentation.model.RepositoryItem
import com.example.desafio_android_accenture.ui.viewholders.RepositoryViewHolder

class RepositoryAdapter(private val manager: AdapterManager) :
    RecyclerView.Adapter<RepositoryViewHolder>() {

    private val repoList = mutableListOf<RepositoryItem>()

    interface AdapterManager {
        fun onRepositoryClick(item: RepositoryItem)
        fun provideImageLoader(): ImageLoader
    }

    fun addRepositories(repositories: List<RepositoryItem>) {
        val diffUtil = RepoItemDiffCallback(repoList, repositories)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        // diffResults.dispatchUpdatesTo(this)
        repoList.clear()
        repoList.addAll(repositories)
        diffResults.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return RepositoryViewHolder(
            layoutInflater.inflate(
                R.layout.item_repository,
                parent,
                false,
            ), manager
        )
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        val item = repoList[position]
        return holder.render(item)
    }

    override fun getItemCount(): Int = repoList.size

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

}