package com.example.desafio_android_accenture.utils.extensions

import android.annotation.SuppressLint
import java.text.Normalizer
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

//yyyy-MM-dd'T'hh:mm:ss.SSSZ
private val ISO8601format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
private val yearFormat = SimpleDateFormat("yyyy", Locale.US)
private val intervalFormat = SimpleDateFormat("mm:ss", Locale.US)

fun String.parseISO8601Date(): Date = ISO8601format.parse(this)!!

fun String.parseISO8601DateToString(): String {
    var convertedDate = Date()
    try {
        convertedDate =
            SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSZ", Locale.getDefault()).parse(this) as Date
    } catch (ignored: ParseException) {
    }
    return SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(convertedDate)
}


@SuppressLint("DefaultLocale")
fun String.normalize(): String = Normalizer
    .normalize(lowercase(), Normalizer.Form.NFD)
    .replace("[^\\p{ASCII}]".toRegex(), "")

fun Long.formatInterval(): String = intervalFormat.format(Date(this))

fun Date.formatYear(): String = yearFormat.format(this)