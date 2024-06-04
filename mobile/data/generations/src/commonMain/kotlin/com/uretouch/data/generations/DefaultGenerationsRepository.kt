package com.uretouch.data.generations

import com.uretouch.data.generations.database.GenerationModeDao
import com.uretouch.data.generations.network.GenerationsApi
import com.uretouch.data.generations.network.model.ApiGenerationRequest
import com.uretouch.data.generations.util.GenerationMappers.toDomain
import com.uretouch.data.generations.util.GenerationMappers.toEntity
import com.uretouch.domain.generations.model.Generation
import com.uretouch.domain.generations.model.GenerationMode
import com.uretouch.domain.generations.repository.GenerationsRepository

internal class DefaultGenerationsRepository(
    private val generationsApi: GenerationsApi,
    private val generationModeDao: GenerationModeDao,
) : GenerationsRepository {
    override suspend fun getHistoryGenerations(): List<Generation> {
        return generationsApi.getGenerationsHistory().map { it.toDomain() }
    }

    override suspend fun getGeneration(id: String): Generation {
        return generationsApi.getGeneration(id = id).toDomain()
    }

    override suspend fun createGeneration(image: ByteArray, prompt: String, modeId: String?): String {
        return generationsApi.createGeneration(ApiGenerationRequest(image = image, prompt = prompt, mode = modeId)).generationId
    }

    override suspend fun getCacheGenerationModes(): List<GenerationMode> {
        return generationModeDao.getAll().map { it.toDomain() }
    }

    override suspend fun getGenerationModes(): List<GenerationMode> {
        return generationsApi.getGenerationModes().map { it.toDomain() }
    }

    override suspend fun saveGenerationModes(modes: List<GenerationMode>) {
        generationModeDao.insert(modes.map { it.toEntity() })
    }
}