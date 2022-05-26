package com.example.desafio_android_accenture.utils.extensions

import android.content.Context
import android.view.View
import android.widget.Toast


fun Context.shortToast(text:String) = Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
fun Context.longToast(text:String) = Toast.makeText(this, text, Toast.LENGTH_SHORT).show()

fun View.shortToast(text:String) = Toast.makeText(this.context, text, Toast.LENGTH_SHORT).show()



