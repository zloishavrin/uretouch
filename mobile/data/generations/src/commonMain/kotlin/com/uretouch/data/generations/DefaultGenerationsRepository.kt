package com.uretouch.data.generations

import com.uretouch.data.generations.network.GenerationsApi
import com.uretouch.data.generations.network.model.ApiGenerationRequest
import com.uretouch.domain.generations.model.Generation
import com.uretouch.domain.generations.repository.GenerationsRepository

internal class DefaultGenerationsRepository(
    private val generationsApi: GenerationsApi,
) : GenerationsRepository {
    override suspend fun getHistoryGenerations(): List<Generation> {
        return generationsApi.getGenerationsHistory().map { it.toDomain() }
    }

    override suspend fun createGeneration(image: ByteArray, prompt: String): String {
        return generationsApi.createGeneration(ApiGenerationRequest(image = image, prompt = prompt)).generationId
    }
}