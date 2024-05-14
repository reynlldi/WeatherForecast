package com.example.weatherforecast.utils

import java.text.SimpleDateFormat
import java.util.Date

fun formatDate(timestamp: Int): String {
    val sdf = SimpleDateFormat("EEEE, d MMM YYYY")
    val date = Date(timestamp.toLong() * 1000)
    return sdf.format(date)
}

fun formatDateTime(timestamp: Int): String {
    val sdf = SimpleDateFormat("hh:mm:aa")
    val date = Date(timestamp.toLong() * 1000)
    return sdf.format(date)
}

fun formatDecimal(item: Double): String {
    return " %.0f".format(item)
}