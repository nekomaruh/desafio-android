package com.example.desafio_android_accenture.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio_android_accenture.R
import com.example.desafio_android_accenture.data.imageloader.ImageLoader
import com.example.desafio_android_accenture.data.imageloader.ImageLoaderService
import com.example.desafio_android_accenture.data.model.RepositoryModel
import com.example.desafio_android_accenture.ui.viewholders.RepositoryViewHolder

// private val onClickListener: (RepositoryModel) -> Unit
class RepositoryAdapter(private val manager: AdapterManager) :
    RecyclerView.Adapter<RepositoryViewHolder>() {

    private val repoList = mutableListOf<RepositoryModel>()

    interface AdapterManager {
        fun onRepositoryClick(item: RepositoryModel)
        fun provideImageLoader(): ImageLoader
    }

    fun addRepositories(repositories: List<RepositoryModel>) {
        val oldList = repoList
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(
            RepoItemDiffCallback(oldList, repositories)
        )
        repoList.addAll(repositories)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return RepositoryViewHolder(
            layoutInflater.inflate(R.layout.item_repository, parent, false),
            manager
        )
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        val item = repoList[position]
        return holder.render(item)
    }

    override fun getItemCount(): Int = repoList.size

    class RepoItemDiffCallback(
        var oldList: List<RepositoryModel>,
        var newList: List<RepositoryModel>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].fullName == newList[oldItemPosition].fullName
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].equals(newList[newItemPosition])
        }
    }

}