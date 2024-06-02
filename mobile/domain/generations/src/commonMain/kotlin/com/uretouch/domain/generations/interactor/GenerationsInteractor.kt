package com.uretouch.domain.generations.interactor

import com.uretouch.domain.generations.model.Generation

interface GenerationsInteractor {
    suspend fun getGenerationsHistory(): List<Generation>
    suspend fun createGeneration(path: String, prompt: String): String
}