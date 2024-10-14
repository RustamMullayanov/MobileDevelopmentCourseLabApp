package com.example.mobiledevelopmentcourselabapp.presentation.view.notifications.service

import android.app.NotificationManager
import androidx.core.content.ContextCompat
import com.example.mobiledevelopmentcourselabapp.presentation.view.notifications.utils.sendNotification
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FcmService : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        val notificationManager = ContextCompat.getSystemService(
            baseContext,
            NotificationManager::class.java
        ) as NotificationManager

        notificationManager.sendNotification(
            messageTitle = message.notification?.title.orEmpty(),
            messageBody = message.notification?.body.orEmpty(),
            applicationContext = baseContext,
            data = message.data
        )
    }
}