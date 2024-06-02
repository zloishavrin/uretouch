package com.uretouch.domain.generations.repository

import com.uretouch.domain.generations.model.Generation

interface GenerationsRepository {
    suspend fun getHistoryGenerations(): List<Generation>
    suspend fun createGeneration(image: ByteArray, prompt: String): String
}