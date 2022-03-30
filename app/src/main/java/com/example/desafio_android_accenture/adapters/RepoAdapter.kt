package com.example.desafio_android_accenture.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio_android_accenture.R
import com.example.desafio_android_accenture.data.models.RepoModel

class RepoAdapter(private val repoList: List<RepoModel>, private val onClickListener:(RepoModel)->Unit): RecyclerView.Adapter<RepoViewHolder>() {
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