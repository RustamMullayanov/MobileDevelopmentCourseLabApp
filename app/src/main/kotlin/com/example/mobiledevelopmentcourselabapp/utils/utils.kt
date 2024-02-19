package com.example.mobiledevelopmentcourselabapp.utils

import android.content.Context

fun Int?.orZero() = this ?: 0

fun Boolean?.orFalse() = this ?: false

fun Context.dpToPx(dp: Int): Int {
    return (dp * resources.displayMetrics.density).toInt()
}