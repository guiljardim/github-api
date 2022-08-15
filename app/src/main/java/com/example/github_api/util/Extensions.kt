package com.example.github_api.util

import android.annotation.SuppressLint
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun String.formatToDate(): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm")
    val date = sdf.parse(this) ?: Date()
    val ts = Timestamp(date.time)
    val output = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    return output.format(ts)

}
