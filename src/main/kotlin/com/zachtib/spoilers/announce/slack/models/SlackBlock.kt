package com.zachtib.spoilers.announce.slack.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
open class SlackBlock(
    val type: String
)