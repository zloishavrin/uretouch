package com.uretouch.data.generations.database

import com.uretouch.common.core.database.GenerationModeEntity

internal interface GenerationModeDao {
    suspend fun deleteAll()
    suspend fun getAll(): List<GenerationModeEntity>
    suspend fun insert(entities: List<GenerationModeEntity>)
}