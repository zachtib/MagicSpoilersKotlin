package com.zachtib.spoilers.database.jasync

import com.github.jasync.sql.db.RowData
import com.zachtib.spoilers.util.toJavaTime
import java.time.LocalDate

fun RowData.getJavaDate(column: String): LocalDate? {
    return getDate(column)?.toLocalDate()?.toJavaTime()
}