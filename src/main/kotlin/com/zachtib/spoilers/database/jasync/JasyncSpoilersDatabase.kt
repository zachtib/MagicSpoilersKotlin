package com.zachtib.spoilers.database.jasync

import com.github.jasync.sql.db.Connection
import com.github.jasync.sql.db.QueryResult
import com.github.jasync.sql.db.RowData
import com.zachtib.spoilers.database.SpoilersDatabase
import com.zachtib.spoilers.models.MagicCard
import com.zachtib.spoilers.models.MagicSet
import kotlinx.coroutines.future.await
import java.time.LocalDate
import javax.inject.Inject


class JasyncSpoilersDatabase @Inject constructor(private val connection: Connection) : SpoilersDatabase {

    companion object {
        const val SELECT_FROM_MAGICSETS = "SELECT * FROM MagicSets"
        const val SELECT_FROM_MAGICSETS_WHERE_WATCHED = "SELECT * FROM MagicSets WHERE watched=1"
        const val SELECT_FROM_MAGICSETS_BY_CODE = "SELECT * FROM MagicSets WHERE code=?"
        const val SELECT_FROM_MAGICCARDS_BY_SETCODE = "SELECT * FROM MagicCards WHERE setCode=?"
        const val INSERT_INTO_MAGICSETS = "INSERT INTO MagicSets (scryfallId, code, name, releaseDate, watched) VALUES (?, ?, ?, ?, ?)"
        const val INSERT_INTO_MAGICCARDS = "INSERT INTO MagicCards (scryfallId, name, setCode) VALUES (?, ?, ?)"

        fun magicSet(rowData: RowData): MagicSet {
            return MagicSet(
                rowData.getString("scryfallId") ?: "",
                rowData.getString("code") ?: "",
                rowData.getString("name") ?: "",
                rowData.getJavaDate("releaseDate") ?: LocalDate.now(),
                rowData.getBoolean("watched") ?: false
            )
        }

        fun magicCard(rowData: RowData): MagicCard {
            return MagicCard(
                rowData.getString("scryfallId") ?: "", //val scryfallId: String,
                rowData.getString("name") ?: "", //val name: String,
                0.toFloat(), //val cmc: Float,
                listOf(), //val colors: List<Char>,
                listOf(), //val colorIndicator: List<Char>?,
                listOf(), //val colorIdentity: List<Char>,
                listOf(), //val cardFaces: List<CardFace>?,
                "", //val manaCost: String,
                "", //val typeLine: String,
                "", //val oracleText: String,
                "", //val power: String?,
                "", //val toughness: String?,
                "", //val loyalty: String?,
                rowData.getString("setCode") ?: "", //val setCode: String,
                ""  //val imageUri: String?
            )
        }
    }

    private suspend fun query(query: String, values: List<Any> = listOf(), release: Boolean = false): QueryResult? {
        return connection.sendPreparedStatement(query, values, release).await()
    }

    override suspend fun getAllSets(): List<MagicSet> {
        return query(SELECT_FROM_MAGICSETS).toList { magicSet(it) } ?: listOf()
    }

    override suspend fun getWatchedSets(): List<MagicSet> {
        return query(SELECT_FROM_MAGICSETS_WHERE_WATCHED).toList { magicSet(it) } ?: listOf()
    }

    override suspend fun getSet(code: String): MagicSet? {
        return query(SELECT_FROM_MAGICSETS_BY_CODE, listOf(code))?.toSingle { magicSet(it) }
    }

    override suspend fun getCardsInSet(set: MagicSet): List<MagicCard> {
        return query(SELECT_FROM_MAGICCARDS_BY_SETCODE)?.toList { magicCard(it) } ?: listOf()
    }

    override suspend fun insertOrUpdate(set: MagicSet) {
        connection.sendPreparedStatement(
            INSERT_INTO_MAGICSETS,
            listOf(set.scryfallId, set.code, set.name, set.releaseDate, set.watched)
        ).await()
    }

    override suspend fun insertCardsInSet(set: MagicSet, cards: List<MagicCard>) {
        for (card in cards) {
            connection.sendPreparedStatement(
                INSERT_INTO_MAGICCARDS,
                listOf(card.scryfallId, card.name, set.code)
            ).await()
        }
    }

}