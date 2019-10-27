package com.zachtib.spoilers.dagger

import com.mongodb.MongoClientURI
import com.zachtib.spoilers.database.SpoilersDatabase
import com.zachtib.spoilers.database.kmongo.MongoDbSpoilersDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import org.litote.kmongo.coroutine.CoroutineClient
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo
import javax.inject.Singleton

@Module(includes = [MongoDbModule.Bindings::class])
class MongoDbModule(private val serverUri: String) {

    @Provides
    @Singleton
    fun provideUri(): MongoClientURI {
        return MongoClientURI(serverUri)
    }

    @Provides
    @Singleton
    fun provideDatabase(client: CoroutineClient, uri: MongoClientURI): CoroutineDatabase {
        return client.getDatabase(uri.database!!)
    }

    @Provides
    @Singleton
    fun provideMongoClient(): CoroutineClient {
        return KMongo.createClient(serverUri).coroutine
    }

    @Module
    interface Bindings {
        @Binds
        fun bindDatabase(database: MongoDbSpoilersDatabase): SpoilersDatabase
    }
}