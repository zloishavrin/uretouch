package com.uretouch.data.generations.network.model

import com.uretouch.domain.generations.model.GenerationStatusType
import kotlinx.serialization.SerialName

enum class ApiGenerationStatusType {
    @SerialName("inProgress")
    InProgress,

    @SerialName("completed")
    Completed,

    @SerialName("failed")
    Failed
}

internal fun ApiGenerationStatusType.toDomain(): GenerationStatusType {
    return when (this) {
        ApiGenerationStatusType.InProgress -> GenerationStatusType.InProgress
        ApiGenerationStatusType.Completed -> GenerationStatusType.Completed
        ApiGenerationStatusType.Failed -> GenerationStatusType.Failed
    }
}