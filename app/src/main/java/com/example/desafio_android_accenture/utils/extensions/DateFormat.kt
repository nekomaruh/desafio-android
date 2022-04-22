package com.example.desafio_android_accenture.utils.extensions

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun String.parseISO8601DateToString(): String {
    var convertedDate = Date()
    try {
        convertedDate =
            SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSZ", Locale.getDefault()).parse(this) as Date
    } catch (ignored: ParseException) {
    }
    return SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(convertedDate)
}