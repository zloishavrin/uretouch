package com.uretouch.data.generations.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiGenerationRequest(
    @SerialName("image") val image: ByteArray,
    @SerialName("prompt") val prompt: String,
    @SerialName("mode") val modeId: String?,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as ApiGenerationRequest

        if (!image.contentEquals(other.image)) return false
        if (prompt != other.prompt) return false
        if (modeId != other.modeId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = image.contentHashCode()
        result = 31 * result + prompt.hashCode()
        result = 31 * result + modeId.hashCode()
        return result
    }
}