package com.example.desafio_android_accenture.ui.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio_android_accenture.data.model.PullRequestModel
import com.example.desafio_android_accenture.databinding.ItemPullRequestBinding
import com.example.desafio_android_accenture.presentation.model.PullRequestItem
import com.example.desafio_android_accenture.ui.adapters.PullRequestAdapter
import com.example.desafio_android_accenture.utils.extensions.parseISO8601DateToString

class PullRequestViewHolder(
    view: View,
    private val manager: PullRequestAdapter.AdapterManager
) :
    BaseViewHolder<PullRequestItem>(view) {
    private val binding = ItemPullRequestBinding.bind(view)

    override fun render(item: PullRequestItem) = with(binding) {
        idPullRequestTitle.text = item.title
        idPullRequestUsername.text = item.user.login
        idPullRequestRealName.text = item.createdAt
        idPullRequestBody.text = item.body
        manager.provideImageLoader().loadCircled(
            context = idPullRequestProfileImg.context,
            path = item.user.avatarUrl,
            imageView = idPullRequestProfileImg,
        )
    }
}