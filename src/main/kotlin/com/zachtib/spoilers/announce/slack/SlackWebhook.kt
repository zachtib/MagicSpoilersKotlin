package com.zachtib.spoilers.announce.slack

import com.zachtib.spoilers.announce.slack.models.SlackMessage
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface SlackWebhook {
    @POST("/services/{webhook_part1}/{webhook_part2}/{webhook_part3}")
    suspend fun postMessage(
        @Path(value = "webhook_part1") part1: String,
        @Path(value = "webhook_part2") part2: String,
        @Path(value = "webhook_part3") part3: String,
        @Body message: SlackMessage
    )
}