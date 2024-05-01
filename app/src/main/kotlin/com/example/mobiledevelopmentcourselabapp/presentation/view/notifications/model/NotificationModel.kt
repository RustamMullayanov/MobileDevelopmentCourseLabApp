package com.example.mobiledevelopmentcourselabapp.presentation.view.notifications.model

import org.threeten.bp.LocalDateTime
import java.io.Serializable

class NotificationModel(
    val id: Int,
    val note: String,
    val dateTime: LocalDateTime
): Serializable