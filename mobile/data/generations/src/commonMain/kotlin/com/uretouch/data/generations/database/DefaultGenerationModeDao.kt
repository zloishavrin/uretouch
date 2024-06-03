package com.uretouch.data.generations.database

import com.uretouch.common.core.database.GenerationModeEntity
import com.uretouch.common.core.database.URetouchDatabase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class DefaultGenerationModeDao(
    private val database: URetouchDatabase,
    private val coroutineDispatcher: CoroutineDispatcher,
) : GenerationModeDao {

    private val queries get() = database.generationModeEntityQueries

    override suspend fun deleteAll() = withContext(coroutineDispatcher) {
        queries.deleteAll()
    }

    override suspend fun getAll(): List<GenerationModeEntity> = withContext(coroutineDispatcher) {
        queries.getAll().executeAsList()
    }

    override suspend fun insert(entities: List<GenerationModeEntity>) = withContext(coroutineDispatcher) {
        queries.transaction {
            entities.forEach { queries.insert(it) }
        }
    }
}