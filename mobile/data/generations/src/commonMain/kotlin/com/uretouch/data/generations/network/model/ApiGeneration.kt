package com.uretouch.data.generations.network.model

import com.uretouch.domain.generations.model.Generation
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiGeneration(
    @SerialName("_id") val id: String,
    @SerialName("status") val status: ApiGenerationStatusType,
    @SerialName("url") val urls: List<String>,
) {
    fun toDomain(): Generation {
        return Generation(
            id = id,
            status = status.toDomain(),
            generationUrls = urls
        )
    }
}