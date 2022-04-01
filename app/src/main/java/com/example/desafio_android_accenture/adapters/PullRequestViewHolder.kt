package com.example.desafio_android_accenture.adapters

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.desafio_android_accenture.data.model.PullRequestModel
import com.example.desafio_android_accenture.databinding.ItemPullRequestBinding
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class PullRequestViewHolder(view: View): RecyclerView.ViewHolder(view){
    private var binding = ItemPullRequestBinding.bind(view)


    private fun formatDate(inputDate: String): String {
        var convertedDate = Date()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSZ", Locale.getDefault())
        try {
            convertedDate = dateFormat.parse(inputDate)
        } catch (ignored: ParseException) { }

        //if you wish to change the parsed date into another formatted date
        val dfOutput = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        return dfOutput.format(convertedDate)
    }



    @SuppressLint("SetTextI18n")
    fun render(pullRequestModel: PullRequestModel){
        binding.idPullRequestTitle.text = pullRequestModel.title
        //binding.idPullRequestBody.text = pullRequestModel.body?: "No comments"

        if(pullRequestModel.body.isNullOrEmpty()){
            binding.idPullRequestBody.text = "No comments"
        }else{
            binding.idPullRequestBody.text = pullRequestModel.body
        }

        binding.idPullRequestUsername.text = pullRequestModel.user.login
        binding.idPullRequestRealName.text = formatDate(pullRequestModel.createdAt)

        Glide.with(binding.idPullRequestProfileImg.context)
            .load(pullRequestModel.user.avatarUrl)
            .transform(CircleCrop())
            .into(binding.idPullRequestProfileImg)
    }
}