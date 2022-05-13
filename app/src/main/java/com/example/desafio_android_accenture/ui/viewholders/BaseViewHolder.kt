package com.example.desafio_android_accenture.ui.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T : Any>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private lateinit var item: T

    fun getItem(): T = item

    open fun render(item: T) {
        this.item = item
    }
}