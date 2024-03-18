package com.example.mobiledevelopmentcourselabapp.domain.model

import org.threeten.bp.LocalDateTime

class ChuckJokeEntity(
    val iconUrl: String,
    val value: String,
    val url: String,
    val updateTime: LocalDateTime
)