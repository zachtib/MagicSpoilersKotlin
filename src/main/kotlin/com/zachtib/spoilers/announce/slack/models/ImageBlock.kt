package com.zachtib.spoilers.announce.slack.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ImageBlock(
    val image_url: String,
    val alt_text: String,
    val type: String = "image"
)