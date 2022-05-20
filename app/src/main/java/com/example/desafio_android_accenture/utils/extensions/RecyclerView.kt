package com.example.desafio_android_accenture.utils.extensions

import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.addVerticalDivider() {
    this.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
}