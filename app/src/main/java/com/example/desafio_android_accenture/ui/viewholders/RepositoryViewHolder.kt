package com.example.desafio_android_accenture.ui.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio_android_accenture.data.model.RepositoryModel
import com.example.desafio_android_accenture.databinding.ItemRepositoryBinding
import com.example.desafio_android_accenture.presentation.model.RepositoryItem
import com.example.desafio_android_accenture.ui.adapters.RepositoryAdapter

class RepositoryViewHolder(
    view: View,
    private val manager: RepositoryAdapter.AdapterManager
) :
    RecyclerView.ViewHolder(view) {
    private val binding = ItemRepositoryBinding.bind(view)

    fun render(repoModel: RepositoryItem) = with(binding) {
        idRepoTitle.text = repoModel.fullName
        idRepoDescription.text = repoModel.description
        idBranchesCount.text = repoModel.branches
        idStarredCount.text = repoModel.stars
        idRepoUsername.text = repoModel.name
        idRepoRealName.text = repoModel.user.login
        manager.provideImageLoader().loadCircled(
            context = idRepoProfileImg.context,
            path = repoModel.user.avatarUrl,
            imageView = binding.idRepoProfileImg
        )
        itemView.setOnClickListener { manager.onRepositoryClick(repoModel) }
    }
}