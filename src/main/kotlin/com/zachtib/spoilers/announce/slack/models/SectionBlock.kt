package com.zachtib.spoilers.announce.slack.models

import com.squareup.moshi.JsonClass

//{
//    "type": "section",
//    "text": {
//    "type": "plain_text",
//    "text": "This is a plain text section block.",
//    "emoji": true
//}
//},
@JsonClass(generateAdapter = true)
data class SectionBlock(
    val text: SectionText,
    val type: String = "section"
)