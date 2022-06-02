package com.example.desafio_android_accenture.data.imageloader

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop

class ImageLoaderImpl : ImageLoader {
    override fun loadCircled(context: Context, path: String, imageView: ImageView) {
        Glide.with(context)
            .load(path)
            .transform(CircleCrop())
            .into(imageView);
    }
}