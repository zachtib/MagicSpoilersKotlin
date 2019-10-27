package com.zachtib.spoilers.scryfall

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.awt.image.CropImageFilter

//    "image_uris": {
//        "small": "https://img.scryfall.com/cards/small/front/b/e/bef16a71-5ed2-4f30-a844-c02a0754f679.jpg?1562853529",
//        "normal": "https://img.scryfall.com/cards/normal/front/b/e/bef16a71-5ed2-4f30-a844-c02a0754f679.jpg?1562853529",
//        "large": "https://img.scryfall.com/cards/large/front/b/e/bef16a71-5ed2-4f30-a844-c02a0754f679.jpg?1562853529",
//        "png": "https://img.scryfall.com/cards/png/front/b/e/bef16a71-5ed2-4f30-a844-c02a0754f679.png?1562853529",
//        "art_crop": "https://img.scryfall.com/cards/art_crop/front/b/e/bef16a71-5ed2-4f30-a844-c02a0754f679.jpg?1562853529",
//        "border_crop": "https://img.scryfall.com/cards/border_crop/front/b/e/bef16a71-5ed2-4f30-a844-c02a0754f679.jpg?1562853529"
//    },

@JsonClass(generateAdapter = true)
data class ScryfallImages(
    val small: String,
    val normal: String,
    val large: String,
    val png: String,
    @Json(name = "art_crop")
    val artCrop: String,
    @Json(name = "border_crop")
    val borderCrop: String
)
