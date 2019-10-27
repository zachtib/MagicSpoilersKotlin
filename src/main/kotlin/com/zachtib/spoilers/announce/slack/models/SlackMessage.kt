package com.zachtib.spoilers.announce.slack.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SlackMessage(
    val text: String,
    val channel: String? = null,
    val blocks: List<Any>? = null
)