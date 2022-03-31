package com.example.desafio_android_accenture.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio_android_accenture.R
import com.example.desafio_android_accenture.data.model.PullRequestModel

class PullRequestAdapter(private val pullRequestList: List<PullRequestModel>): RecyclerView.Adapter<PullRequestViewHolder>() {
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