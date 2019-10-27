package com.zachtib.spoilers.dagger

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.zachtib.spoilers.logging.Logger
import com.zachtib.spoilers.logging.getOkHttpLogger
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
object HttpModule {
    @Provides
    @Singleton
    @JvmStatic
    fun provideConverterFactory(): Converter.Factory {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        return MoshiConverterFactory.create(moshi)
    }

    @Provides
    @Singleton
    @JvmStatic
    fun provideOkHttpClient(logger: Logger): OkHttpClient {
        val interceptor = HttpLoggingInterceptor(logger.getOkHttpLogger())
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }
}