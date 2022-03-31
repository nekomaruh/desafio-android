package com.example.desafio_android_accenture.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio_android_accenture.R
import com.example.desafio_android_accenture.data.model.PullRequestModel

// private val pullRequestList: List<PullRequestModel>

class PullRequestAdapter(): RecyclerView.Adapter<PullRequestViewHolder>() {
    var pullRequestList = mutableListOf<PullRequestModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun addPullRequests(pullRequests: List<PullRequestModel>){
        pullRequestList.addAll(pullRequests)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullRequestViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PullRequestViewHolder(layoutInflater.inflate(R.layout.item_pull_request, parent, false))
    }

    override fun onBindViewHolder(holder: PullRequestViewHolder, position: Int) {
        val item = pullRequestList[position]
        return holder.render(item)
    }

    override fun getItemCount(): Int = pullRequestList.size
}