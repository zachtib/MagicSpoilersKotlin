package com.zachtib.spoilers.announce.slack

interface SlackConfigService {
    suspend fun getConfigs(): List<SlackConfig>
}