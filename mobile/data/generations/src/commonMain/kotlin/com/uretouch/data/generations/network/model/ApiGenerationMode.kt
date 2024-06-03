package com.uretouch.data.generations.network.model

import com.uretouch.domain.generations.model.GenerationMode
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiGenerationMode(
    @SerialName("_id") val id: String,
    @SerialName("name") val name: String,
    @SerialName("description") val description: String,
) {
    fun toDomain(): GenerationMode {
        return GenerationMode(
            id = id,
            name = name,
            description = description
        )
    }
}