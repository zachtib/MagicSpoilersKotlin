package com.zachtib.spoilers.scryfall

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ScryfallCardFace(
    val name: String,
    val colors: List<Char>?,
    @Json(name = "color_indicator")
    val colorIndicator: List<Char>?,
    val loyalty: String?,
    @Json(name = "mana_cost")
    val manaCost: String?,
    @Json(name = "oracle_text")
    val oracleText: String?,
    val power: String?,
    val toughness: String?,
    @Json(name = "type_line")
    val typeLine: String,
    @Json(name = "image_uris")
    val imageUris: ScryfallImages?
)