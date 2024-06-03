package com.uretouch.data.generations.network

import com.uretouch.data.generations.network.model.ApiGeneration
import com.uretouch.data.generations.network.model.ApiGenerationMode
import com.uretouch.data.generations.network.model.ApiGenerationRequest
import com.uretouch.data.generations.network.model.ApiGenerationResponse

internal interface GenerationsApi {
    suspend fun getGenerationsHistory(): List<ApiGeneration>
    suspend fun getGeneration(id: String): ApiGeneration
    suspend fun createGeneration(request: ApiGenerationRequest): ApiGenerationResponse
    suspend fun getGenerationModes(): List<ApiGenerationMode>
}