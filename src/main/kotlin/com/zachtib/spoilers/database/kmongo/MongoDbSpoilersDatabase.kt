package com.zachtib.spoilers.database.kmongo

import com.zachtib.spoilers.database.SpoilersDatabase
import com.zachtib.spoilers.models.MagicCard
import com.zachtib.spoilers.models.MagicSet
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq
import javax.inject.Inject

class MongoDbSpoilersDatabase @Inject constructor(private val database: CoroutineDatabase) : SpoilersDatabase {

    private val setCollection = database.getCollection<MagicSet>()
    private val cardCollection = database.getCollection<MagicCard>()

    override suspend fun getAllSets(): List<MagicSet> {
        return setCollection.find().toList()
    }

    override suspend fun getWatchedSets(): List<MagicSet> {
        return setCollection.find(MagicSet::watched eq true).toList()
    }

    override suspend fun getSet(code: String): MagicSet? {
        return setCollection.findOne(MagicSet::code eq code)
    }

    override suspend fun getCardsInSet(set: MagicSet): List<MagicCard> {
        return cardCollection.find(MagicCard::setCode eq set.code).toList()
    }

    override suspend fun insertOrUpdate(set: MagicSet) {
        setCollection.save(set)
    }

    override suspend fun insertCardsInSet(set: MagicSet, cards: List<MagicCard>) {
        val cardsWithSetCode = cards.map { it.copy(setCode = set.code) }
        if (cards.isNotEmpty()) {
            cardCollection.insertMany(cardsWithSetCode)
        }
    }
}