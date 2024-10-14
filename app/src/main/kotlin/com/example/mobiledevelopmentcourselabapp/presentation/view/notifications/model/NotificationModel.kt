package com.example.mobiledevelopmentcourselabapp.presentation.view.notifications.model

import org.threeten.bp.LocalDateTime
import java.io.Serializable

class NotificationModel(
    var note: String,
    var dateTime: LocalDateTime
): Serializable

class NotificationDbModel(
    var note: String = "",
    var dateTime: String = ""
): Serializable