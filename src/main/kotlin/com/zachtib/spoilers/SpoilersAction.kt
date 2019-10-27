package com.zachtib.spoilers

sealed class SpoilersAction {
    object UpdateWatchedSets : SpoilersAction()
    object CheckWatchedSetsForSpoilers : SpoilersAction()
    data class CheckIndividualSet(val code: String) : SpoilersAction()
}