package com.example.desafio_android_accenture.ui.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio_android_accenture.data.model.PullRequestModel
import com.example.desafio_android_accenture.databinding.ItemPullRequestBinding
import com.example.desafio_android_accenture.ui.adapters.PullRequestAdapter
import com.example.desafio_android_accenture.utils.extensions.parseISO8601DateToString

class PullRequestViewHolder(
    view: View,
    private val manager: PullRequestAdapter.AdapterManager
) :
    RecyclerView.ViewHolder(view) {
    private val binding = ItemPullRequestBinding.bind(view)

    fun render(pullRequestModel: PullRequestModel) {
        with(binding) {
            idPullRequestTitle.text = pullRequestModel.title
            idPullRequestUsername.text = pullRequestModel.user.login
            idPullRequestRealName.text = pullRequestModel.createdAt.parseISO8601DateToString()
            idPullRequestBody.text = pullRequestModel.body ?: "No comments"
            manager.provideImageLoader().loadCircled(
                context = idPullRequestProfileImg.context,
                path = pullRequestModel.user.avatarUrl,
                imageView = idPullRequestProfileImg,
            )
        }
    }
}