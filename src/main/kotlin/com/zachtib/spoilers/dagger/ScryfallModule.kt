package com.zachtib.spoilers.dagger

import com.zachtib.spoilers.scryfall.ScryfallApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
object ScryfallModule {
    @Provides
    @Singleton
    @JvmStatic
    fun provideScryfallApi(converterFactory: Converter.Factory, httpClient: OkHttpClient): ScryfallApi {
        return Retrofit.Builder()
            .addConverterFactory(converterFactory)
            .baseUrl("https://api.scryfall.com/")
            .client(httpClient)
            .build()
            .create()
    }
}