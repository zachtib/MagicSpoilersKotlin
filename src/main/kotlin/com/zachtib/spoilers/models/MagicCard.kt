package com.zachtib.spoilers.models

data class MagicCard(
    val scryfallId: String,
    val name: String,
    val cmc: Float,
    val colors: List<Char>,
    val colorIndicator: List<Char>?,
    val colorIdentity: List<Char>,
    val cardFaces: List<CardFace>?,
    val manaCost: String,
    val typeLine: String,
    val oracleText: String,
    val power: String?,
    val toughness: String?,
    val loyalty: String?,
    val setCode: String,
    val imageUri: String?
)