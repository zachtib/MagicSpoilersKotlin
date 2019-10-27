package com.zachtib.spoilers.models

data class CardFace(
    val name: String,
    val manaCost: String,
    val colors: List<Char>,
    val colorIndicator: List<Char>?,
    val typeLine: String,
    val oracleText: String,
    val power: String?,
    val toughness: String?,
    val loyalty: String?,
    val imageUri: String?
)