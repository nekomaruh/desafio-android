package com.example.desafio_android_accenture.ui.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio_android_accenture.data.imageloader.ImageLoader
import com.example.desafio_android_accenture.data.imageloader.ImageLoaderService
import com.example.desafio_android_accenture.data.model.RepositoryModel
import com.example.desafio_android_accenture.databinding.ItemRepositoryBinding
import com.example.desafio_android_accenture.ui.adapters.RepositoryAdapter
import kotlinx.coroutines.*


class RepositoryViewHolder(
    view: View,
    private val manager: RepositoryAdapter.AdapterManager
) : RecyclerView.ViewHolder(view) {

    private val binding = ItemRepositoryBinding.bind(view)

    fun render(repoModel: RepositoryModel) {
        binding.idRepoTitle.text = repoModel.fullName
        binding.idRepoDescription.text = repoModel.description
        binding.idBranchesCount.text = repoModel.branches
        binding.idStarredCount.text = repoModel.stars
        binding.idRepoUsername.text = repoModel.name
        binding.idRepoRealName.text = repoModel.user.login

        manager.provideImageLoader().loadCircled(
            binding.idRepoProfileImg.context,
            repoModel.user.avatarUrl,
            binding.idRepoProfileImg
        )

        itemView.setOnClickListener { manager.onRepositoryClick(repoModel) }
    }
}