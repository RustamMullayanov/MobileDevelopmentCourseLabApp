package com.example.mobiledevelopmentcourselabapp.presentation.view.notifications.utils

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.mobiledevelopmentcourselabapp.MainActivity
import com.example.mobiledevelopmentcourselabapp.R

fun NotificationManager.sendNotification(
    messageTitle: String,
    messageBody: String,
    applicationContext: Context,
    data: Map<String, String>? = null
) {
    val activityIntent = Intent(applicationContext, MainActivity::class.java)

    val builder = NotificationCompat.Builder(
        applicationContext,
        applicationContext.getString(R.string.notifications_channel_id)
    )

    builder.setSmallIcon(R.drawable.ic_third_fragment_black_24dp)
        .setContentTitle(messageTitle)
        .setContentText(messageBody)
        .setContentIntent(
            PendingIntent.getActivity(
                applicationContext,
                0,
                activityIntent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        )
        .setContentText(messageBody)

    notify(0, builder.build())
}