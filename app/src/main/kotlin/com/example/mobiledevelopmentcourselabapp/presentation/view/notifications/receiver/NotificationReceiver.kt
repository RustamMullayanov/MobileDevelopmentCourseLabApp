package com.example.mobiledevelopmentcourselabapp.presentation.view.notifications.receiver

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.mobiledevelopmentcourselabapp.presentation.view.notifications.utils.sendNotification

class NotificationsReceiver : BroadcastReceiver() {

    // в главном потоке
    override fun onReceive(context: Context, intent: Intent) {

        Log.d("TAG", "onReceive")

        val notificationManager = ContextCompat.getSystemService(
            context,
            NotificationManager::class.java
        ) as NotificationManager

        val text = intent.getStringExtra("NOTIFICATION").orEmpty()

        notificationManager.sendNotification(
            messageTitle = "Сработало напоминание",
            messageBody = text,
            applicationContext = context,
        )
    }


}