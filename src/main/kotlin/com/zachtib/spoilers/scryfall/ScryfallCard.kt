package com.zachtib.spoilers.scryfall

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ScryfallCard(
    val id: String,
    val lang: String,
    @Json(name = "oracle_id")
    val oracleId: String,
    val uri: String,
    val name: String,
    @Json(name = "scryfall_uri")
    val scryfallUri: String,
    @Json(name = "tcgplayer_id")
    val tcgPlayerId: Int?,
    @Json(name = "card_faces")
    val cardFaces: List<ScryfallCardFace>?,
    val cmc: Float,
    val colors: List<Char>?,
    @Json(name = "color_identity")
    val colorIdentity: List<Char>,
    @Json(name = "color_indicator")
    val colorIndicator: List<Char>?,
    @Json(name = "edhrec_rank")
    val edhrecRank: Int?,
    val foil: Boolean,
    val nonfoil: Boolean,
    val layout: String,
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
    val images: ScryfallImages?
)