package com.zachtib.spoilers.models

import java.time.LocalDate

data class MagicSet(
    val scryfallId: String,
    val code: String,
    val name: String,
    val releaseDate: LocalDate?,
    val watched: Boolean
)