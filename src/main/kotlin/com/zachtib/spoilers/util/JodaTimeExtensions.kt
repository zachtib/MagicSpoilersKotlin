package com.zachtib.spoilers.util

import org.joda.time.LocalDate

fun LocalDate.toJavaTime(): java.time.LocalDate {
    return java.time.LocalDate.of(year, monthOfYear, dayOfMonth)
}
