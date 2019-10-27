package com.zachtib.spoilers.announce.slack.models

import com.squareup.moshi.JsonClass

//{
//    "type": "section",
//    "text": {
//        "type": "mrkdwn",
//        "text": "You can add an image next to text in this block."
//    },
//    "accessory": {
//        "type": "image",
//        "image_url": "https://api.slack.com/img/blocks/bkb_template_images/plants.png",
//        "alt_text": "plants"
//    }
//}

@JsonClass(generateAdapter = true)
data class SectionWithImage(
    val text: SectionText,
    val accessory: ImageBlock,
    val type: String = "section"
)