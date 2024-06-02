package com.uretouch.data.generations.network

import com.uretouch.data.generations.network.model.ApiGeneration
import com.uretouch.data.generations.network.model.ApiGenerationRequest
import com.uretouch.data.generations.network.model.ApiGenerationResponse

internal interface GenerationsApi {
    suspend fun getGenerationsHistory(): List<ApiGeneration>
    suspend fun getGenerations(ids: List<String>): List<ApiGeneration>
    suspend fun createGeneration(request: ApiGenerationRequest): ApiGenerationResponse
}