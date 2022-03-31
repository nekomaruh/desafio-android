package com.example.desafio_android_accenture.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.desafio_android_accenture.data.model.RepositoryModel
import com.example.desafio_android_accenture.databinding.ItemRepositoryBinding

class RepoViewHolder(view: View):RecyclerView.ViewHolder(view){
    private var binding = ItemRepositoryBinding.bind(view)

    fun render(repoModel: RepositoryModel, onClickListener: (RepositoryModel) -> Unit){
        binding.idRepoTitle.text = repoModel.name
        binding.idRepoDescription.text = repoModel.description
        binding.idBranchesCount.text = repoModel.branches
        binding.idStarredCount.text = repoModel.stars
        binding.idRepoUsername.text = repoModel.fullName
        binding.idRepoRealName.text = repoModel.name
        Glide.with(binding.idRepoProfileImg.context)
            .load("https://picsum.photos/200")
            .transform(CircleCrop())
            .into(binding.idRepoProfileImg)
        itemView.setOnClickListener { onClickListener(repoModel) }
    }
}