package com.uretouch.domain.generations.interactor

import com.uretouch.domain.generations.model.Generation
import com.uretouch.domain.generations.model.GenerationMode

interface GenerationsInteractor {
    suspend fun getGenerationsHistory(): List<Generation>
    suspend fun getGeneration(id: String): Generation
    suspend fun createGeneration(path: String, prompt: String, modeId: String?): String
    suspend fun getGenerationModes(): List<GenerationMode>

    suspend fun saveGenerationIntoDevice(generationUrl: String, prompt: String)
}