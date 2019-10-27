package com.zachtib.spoilers.dagger

import com.zachtib.spoilers.announce.AnnounceChannel
import com.zachtib.spoilers.announce.slack.SlackChannel
import com.zachtib.spoilers.announce.slack.SlackConfig
import com.zachtib.spoilers.announce.slack.SlackConfigService
import com.zachtib.spoilers.announce.slack.SlackWebhook
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.create

@Module
abstract class SlackModule {
    @Binds
    @IntoSet
    abstract fun bindSlackChannel(channel: SlackChannel): AnnounceChannel

    @Module
    companion object {
        @Provides
        @IntoSet
        @JvmStatic
        fun provideSlackConfig(): SlackConfig {
            return SlackConfig(
                auth = System.getenv("SLACK_AUTHORIZATION"),
                channel = System.getenv("SLACK_CHANNEL")
            )
        }

        @Provides
        @JvmStatic
        fun provideSlackConfigService(configs: Set<SlackConfig>): SlackConfigService {
            return object : SlackConfigService {
                val list = configs.toList()
                override suspend fun getConfigs(): List<SlackConfig> {
                    return list
                }
            }
        }
        
        @Provides
        @JvmStatic
        fun provideSlackWebHook(converterFactory: Converter.Factory, httpClient: OkHttpClient): SlackWebhook {
            return Retrofit.Builder()
                .addConverterFactory(converterFactory)
                .baseUrl("https://hooks.slack.com/")
                .client(httpClient)
                .build()
                .create()
        }
    }


}