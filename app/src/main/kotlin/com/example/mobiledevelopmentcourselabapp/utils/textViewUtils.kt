package com.example.mobiledevelopmentcourselabapp.utils

import android.text.Selection
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView

fun TextView.makeLinks(links: Map<String, () -> Unit>) {
    val spannableString = SpannableString(this.text)
    links.forEach { link ->
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(view: View) {
                Selection.setSelection((view as TextView).text as Spannable, 0)
                view.invalidate()
                link.value.invoke()
            }

            override fun updateDrawState(textPaint: TextPaint) {
                textPaint.color = textPaint.linkColor
                textPaint.isUnderlineText = true
            }
        }
        val startIndexOfLink = this.text.toString().indexOf(link.key)
        if (startIndexOfLink == -1) return@forEach

        spannableString.setSpan(
            clickableSpan, startIndexOfLink, startIndexOfLink + link.key.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        this.movementMethod = LinkMovementMethod.getInstance()
        this.setText(spannableString, TextView.BufferType.SPANNABLE)
    }
}