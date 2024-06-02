package com.uretouch.data.generations.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiGenerationResponse(
    @SerialName("generation_id") val generationId: String,
)