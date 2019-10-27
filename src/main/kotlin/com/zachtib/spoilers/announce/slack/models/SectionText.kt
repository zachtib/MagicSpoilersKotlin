package com.zachtib.spoilers.announce.slack.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SectionText(
    val text: String,
    val emoji: Boolean = true,
    val type: String = "plain_text"
)
