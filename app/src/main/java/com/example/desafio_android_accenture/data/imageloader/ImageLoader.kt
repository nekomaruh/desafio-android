package com.example.desafio_android_accenture.data.imageloader

import android.content.Context
import android.widget.ImageView

interface ImageLoader {
    fun loadCircled(
        context: Context,
        path: String,
        imageView: ImageView
    )
}