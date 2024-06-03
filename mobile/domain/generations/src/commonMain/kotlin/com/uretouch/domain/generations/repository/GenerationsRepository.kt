package com.uretouch.domain.generations.repository

import com.uretouch.domain.generations.model.Generation
import com.uretouch.domain.generations.model.GenerationMode

interface GenerationsRepository {
    suspend fun getHistoryGenerations(): List<Generation>
    suspend fun getGeneration(id: String): Generation
    suspend fun createGeneration(image: ByteArray, prompt: String, modeId: String?): String
    suspend fun getCacheGenerationModes(): List<GenerationMode>
    suspend fun getGenerationModes(): List<GenerationMode>
    suspend fun saveGenerationModes(modes: List<GenerationMode>)
}