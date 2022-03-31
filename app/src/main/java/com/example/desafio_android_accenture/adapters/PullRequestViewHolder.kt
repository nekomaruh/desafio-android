package com.example.desafio_android_accenture.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.desafio_android_accenture.data.model.PullRequestModel
import com.example.desafio_android_accenture.databinding.ItemPullRequestBinding

class PullRequestViewHolder(view: View): RecyclerView.ViewHolder(view){
    private var binding = ItemPullRequestBinding.bind(view)

    fun render(pullRequestModel: PullRequestModel){
        binding.idPullRequestTitle.text = pullRequestModel.title
        binding.idPullRequestBody.text = pullRequestModel.body
        binding.idPullRequestUsername.text = pullRequestModel.title
        binding.idPullRequestRealName.text = pullRequestModel.user.login
        Glide.with(binding.idPullRequestProfileImg.context)
            .load("https://picsum.photos/200")
            .transform(CircleCrop())
            .into(binding.idPullRequestProfileImg)
    }
}