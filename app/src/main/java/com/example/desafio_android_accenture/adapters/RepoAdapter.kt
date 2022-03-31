package com.example.desafio_android_accenture.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio_android_accenture.R
import com.example.desafio_android_accenture.data.model.RepositoryModel

class RepoAdapter(private val onClickListener:(RepositoryModel)->Unit): RecyclerView.Adapter<RepoViewHolder>() {
    var repoList = mutableListOf<RepositoryModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun addRepositories(repositories: List<RepositoryModel>){
        repoList.addAll(repositories)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return RepoViewHolder(layoutInflater.inflate(R.layout.item_repository, parent, false))
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val item = repoList[position]
        return holder.render(item,onClickListener)
    }

    override fun getItemCount(): Int = repoList.size

}