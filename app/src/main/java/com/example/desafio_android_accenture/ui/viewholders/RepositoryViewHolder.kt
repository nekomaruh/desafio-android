package com.example.desafio_android_accenture.ui.viewholders

import android.view.View
import com.example.desafio_android_accenture.databinding.ItemRepositoryBinding
import com.example.desafio_android_accenture.presentation.model.RepositoryItem
import com.example.desafio_android_accenture.ui.adapters.RepositoryAdapter

class RepositoryViewHolder(
    view: View,
    private val manager: RepositoryAdapter.AdapterManager
) :
    BaseViewHolder<RepositoryItem>(view) {
    private val binding = ItemRepositoryBinding.bind(view)

    override fun render(item: RepositoryItem) = with(binding) {
        idRepoTitle.text = item.fullName
        idRepoDescription.text = item.description
        idBranchesCount.text = item.branches
        idStarredCount.text = item.stars
        idRepoUsername.text = item.name
        idRepoRealName.text = item.user.login
        manager.provideImageLoader().loadCircled(
            context = idRepoProfileImg.context,
            path = item.user.avatarUrl,
            imageView = binding.idRepoProfileImg
        )
        itemView.setOnClickListener { manager.onRepositoryClick(item) }
    }
}