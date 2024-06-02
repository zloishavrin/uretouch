package com.uretouch.feature.history.logic.history.api.state

import com.uretouch.domain.generations.model.Generation
import com.uretouch.domain.generations.model.GenerationStatusType

data class GenerationUi(
    val id: String,
    val url: String,
    val status: Status,
) {
    enum class Status {
        InProgress,
        Completed,
        Failed
    }
}

internal fun Generation.toUi(): GenerationUi {
    return GenerationUi(
        id = id,
        url = generationUrls.firstOrNull() ?: "",
        status = status.toUi()
    )
}

internal fun GenerationStatusType.toUi(): GenerationUi.Status {
    return when (this) {
        GenerationStatusType.InProgress -> GenerationUi.Status.InProgress
        GenerationStatusType.Completed -> GenerationUi.Status.Completed
        GenerationStatusType.Failed -> GenerationUi.Status.Failed
    }
}