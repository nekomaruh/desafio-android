package com.example.desafio_android_accenture.ui.adapters

import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio_android_accenture.ui.viewholders.BaseViewHolder

abstract class BaseAdapter<T : Any> : RecyclerView.Adapter<BaseViewHolder<T>>() {
    private val list = mutableListOf<T>()

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        val item = list[position]
        return holder.render(item)
    }

    fun addItems(items: List<T>) {
        val diffUtil = BaseDiffCallback(list, items)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        diffResults.dispatchUpdatesTo(this)
        list.clear()
        list.addAll(items)
        //diffResults.dispatchUpdatesTo(this)
    }


}

