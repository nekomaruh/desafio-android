package com.example.desafio_android_accenture.ui.viewholders

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio_android_accenture.data.imageloader.ImageLoader
import com.example.desafio_android_accenture.data.imageloader.ImageLoaderService
import com.example.desafio_android_accenture.data.model.PullRequestModel
import com.example.desafio_android_accenture.databinding.ItemPullRequestBinding
import com.example.desafio_android_accenture.ui.adapters.PullRequestAdapter
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class PullRequestViewHolder(
    view: View,
    private val manager: PullRequestAdapter.AdapterManager
) :
    RecyclerView.ViewHolder(view) {
    private val binding = ItemPullRequestBinding.bind(view)

    private fun formatDate(inputDate: String): String {
        var convertedDate = Date()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSZ", Locale.getDefault())
        try {
            convertedDate = dateFormat.parse(inputDate) as Date
        } catch (ignored: ParseException) {
        }

        //if you wish to change the parsed date into another formatted date
        val dfOutput = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        return dfOutput.format(convertedDate)
    }

    @SuppressLint("SetTextI18n")
    fun render(pullRequestModel: PullRequestModel) {
        with(binding) {
            idPullRequestTitle.text = pullRequestModel.title
            idPullRequestUsername.text = pullRequestModel.user.login
            idPullRequestRealName.text = formatDate(pullRequestModel.createdAt)
        }

        if (pullRequestModel.body.isNullOrEmpty()) {
            binding.idPullRequestBody.text = "No comments"
        } else {
            binding.idPullRequestBody.text = pullRequestModel.body
        }

        manager.provideImageLoader().loadCircled(
            binding.idPullRequestProfileImg.context,
            pullRequestModel.user.avatarUrl,
            binding.idPullRequestProfileImg,
        )
    }
}