package com.example.mobiledevelopmentcourselabapp.utils

import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.DateTimeParseException
import java.util.Locale


fun formatDateTimeToUiDateTime(dateTime: LocalDateTime): String = formatDateTime(dateTime, "d MMM yyyy, hh:mm")

private fun formatDateTime(dateTime: LocalDateTime, format: String): String =
    DateTimeFormatter.ofPattern(format)
        .withLocale(Locale("ru"))
        .format(dateTime)
fun tryParseServerDateTime(date: String) = try {
    LocalDateTime.parse(date, DateTimeFormatter.ofPattern(SERVER_DATE_FORMAT))
} catch (e: DateTimeParseException) {
    null
}

const val SERVER_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSSSSS"