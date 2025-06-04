package com.est.curdsample.util

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

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
