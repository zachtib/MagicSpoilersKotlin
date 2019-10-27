package com.zachtib.spoilers.scryfall

import com.squareup.moshi.Json

data class ScryfallResponse<T>(
    @Json(name = "object")
    val kind: String,
    @Json(name = "has_more")
    val hasMore: Boolean,
    val data: T
)