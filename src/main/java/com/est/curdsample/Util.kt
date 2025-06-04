package com.est.curdsample

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun priorityResolve(priority: Int): String {
    return when (priority) {
        0 -> " "
        1 -> "text-red-700"
        2 -> "text-red-500"
        3 -> "text-yellow-500"
        4 -> "text-green-500"
        else -> "text-green-700"
    }
}

private val pattern: DateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy")
fun convertToLocalDate(localDateStr: String?): LocalDate {
    return LocalDate.parse(localDateStr, pattern)
}

fun convertToString(localDate: LocalDate): String {
    return localDate.format(pattern)
}

fun convertToString(localDateTime: LocalDateTime): String {
    return localDateTime.format(pattern)
}