package com.zachtib.spoilers

import com.zachtib.spoilers.logging.Logger
import com.zachtib.spoilers.service.SpoilerService
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class SpoilersApp @Inject constructor(
    private val service: SpoilerService,
    private val logger: Logger
) {

    suspend fun handleAction(action: SpoilersAction) = when(action) {
        SpoilersAction.UpdateWatchedSets -> service.updateTrackedSets()
        SpoilersAction.CheckWatchedSetsForSpoilers -> service.refreshAllWatchedSets()
        is SpoilersAction.CheckIndividualSet -> service.refreshSpoilersForSetCode(action.code)
    }

    fun handleCommandLine(args: Array<String>) {
        logger.debug("Handling command line arguments: (${args.joinToString()})")
        val action: SpoilersAction = when (args.firstOrNull()) {
            "refreshsets" -> SpoilersAction.UpdateWatchedSets
            "", null -> SpoilersAction.CheckWatchedSetsForSpoilers
            else -> throw IllegalArgumentException("Unknown action: $args")
        }
        runBlocking {
            handleAction(action)
        }
    }
}