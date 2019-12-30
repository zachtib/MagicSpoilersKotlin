package com.zachtib.spoilers.database.jasync

import com.github.jasync.sql.db.QueryResult
import com.github.jasync.sql.db.RowData

fun <T> QueryResult?.toSingle(converter: (RowData) -> T): T? {
    return this?.rows?.firstOrNull()?.let(converter)
}

fun <T> QueryResult?.toList(converter: (RowData) -> T): List<T>? {
    return this?.rows?.map(converter)
}