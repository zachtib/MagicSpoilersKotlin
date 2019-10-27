package com.zachtib.spoilers.scryfall

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ScryfallApi {
    @GET("sets")
    suspend fun sets(): ScryfallResponse<List<ScryfallSet>>

    @GET("cards/search")
    suspend fun cardsSearch(
        @Query("q") query: String,
        @QueryMap queryMap: Map<String, String> = mapOf()
    ): ScryfallResponse<List<ScryfallCard>>
}