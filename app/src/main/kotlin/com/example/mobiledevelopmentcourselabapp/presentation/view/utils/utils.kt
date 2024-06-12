package com.example.mobiledevelopmentcourselabapp.presentation.view.utils
import android.content.Context
fun Int?.orZero() = this ?: 0
fun Boolean?.orFalse() = this ?: false

fun Context.dpToPx(dp: Int): Int {
    return (dp * resources.displayMetrics.density).toInt()
}