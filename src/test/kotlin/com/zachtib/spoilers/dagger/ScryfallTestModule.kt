package com.zachtib.spoilers.dagger

import com.zachtib.spoilers.scryfall.ScryfallApi
import com.zachtib.spoilers.scryfall.ScryfallCard
import com.zachtib.spoilers.scryfall.ScryfallResponse
import com.zachtib.spoilers.scryfall.ScryfallSet
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ScryfallTestModule(val scryfallApi: ScryfallApi) {
    @Provides
    @Singleton
    fun provideScryfallApi(): ScryfallApi {
        return scryfallApi
    }

    companion object {
        fun empty() = ScryfallTestModule(object : ScryfallApi {
            override suspend fun sets(): ScryfallResponse<List<ScryfallSet>> {
                return ScryfallResponse("set", false, listOf())
            }

            override suspend fun cardsSearch(
                query: String,
                queryMap: Map<String, String>
            ): ScryfallResponse<List<ScryfallCard>> {
                return ScryfallResponse("card", false, listOf())
            }
        })
    }
}
