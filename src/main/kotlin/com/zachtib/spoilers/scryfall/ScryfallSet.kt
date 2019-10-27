package com.zachtib.spoilers.scryfall

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

// Example object:
//{
//    "object": "set",
//    "id": "288bd996-960e-448b-a187-9504c1930c2c",
//    "code": "lea",
//    "tcgplayer_id": 7,
//    "name": "Limited Edition Alpha",
//    "uri": "https://api.scryfall.com/sets/288bd996-960e-448b-a187-9504c1930c2c",
//    "scryfall_uri": "https://scryfall.com/sets/lea",
//    "search_uri": "https://api.scryfall.com/cards/search?order=set&q=e%3Alea&unique=prints",
//    "released_at": "1993-08-05",
//    "set_type": "core",
//    "card_count": 295,
//    "digital": false,
//    "foil_only": false,
//    "block_code": "lea",
//    "block": "Core Set",
//    "icon_svg_uri": "https://img.scryfall.com/sets/lea.svg?1570420800"
//}

@JsonClass(generateAdapter = true)
data class ScryfallSet(
    val id: String,
    val code: String,
    @Json(name = "tcgplayer_id")
    val tcgPlayerId: Int?,
    val name: String,
    val uri: String,
    @Json(name = "scryfall_uri")
    val scryfallUri: String,
    @Json(name = "search_uri")
    val searchUri: String,
    @Json(name = "released_at")
    val releasedAt: String?,
    @Json(name = "set_type")
    val setType: String,
    @Json(name = "card_count")
    val cardCount: Int,
    val digital: Boolean,
    @Json(name = "foil_only")
    val foilOnly: Boolean,
    @Json(name = "block_code")
    val blockCode: String?,
    val block: String?,
    @Json(name = "icon_svg_uri")
    val iconSvgUri: String
)