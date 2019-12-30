package com.zachtib.spoilers.dagger

import com.github.jasync.sql.db.Connection
import com.github.jasync.sql.db.postgresql.PostgreSQLConnectionBuilder
import com.zachtib.spoilers.database.SpoilersDatabase
import com.zachtib.spoilers.database.jasync.JasyncSpoilersDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [JasyncModule.Bindings::class])
class JasyncModule(
    private val host: String,
    private val port: String,
    private val database: String,
    private val username: String,
    private val password: String
) {

    @Provides
    @Singleton
    fun provideConnection(): Connection {
        return PostgreSQLConnectionBuilder.createConnectionPool(
            "jdbc:postgresql://$host:$port/$database?user=$username&password=$password"
        )
    }

    @Module
    interface Bindings {
        @Binds
        fun bindDatabase(database: JasyncSpoilersDatabase): SpoilersDatabase
    }
}