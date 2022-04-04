package com.example.desafio_android_accenture.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio_android_accenture.data.imageloader.ImageLoader
import com.example.desafio_android_accenture.data.imageloader.ImageLoaderService
import com.example.desafio_android_accenture.data.model.RepositoryModel
import com.example.desafio_android_accenture.databinding.ItemRepositoryBinding


class RepositoryViewHolder(view: View):RecyclerView.ViewHolder(view){
    private val binding = ItemRepositoryBinding.bind(view)
    private val imageLoader: ImageLoader = ImageLoaderService()

    fun render(repoModel: RepositoryModel, onClickListener: (RepositoryModel) -> Unit){
        binding.idRepoTitle.text = repoModel.fullName
        binding.idRepoDescription.text = repoModel.description
        binding.idBranchesCount.text = repoModel.branches
        binding.idStarredCount.text = repoModel.stars
        binding.idRepoUsername.text = repoModel.name
        binding.idRepoRealName.text = repoModel.user.login

        imageLoader.loadCircled(
            binding.idRepoProfileImg.context,
            repoModel.user.avatarUrl,
            binding.idRepoProfileImg)

        itemView.setOnClickListener { onClickListener(repoModel) }
    }
}