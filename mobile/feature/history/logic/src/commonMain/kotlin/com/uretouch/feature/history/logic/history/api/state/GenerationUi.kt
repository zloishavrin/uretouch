package com.uretouch.feature.history.logic.history.api.state

import com.uretouch.domain.generations.model.Generation
import com.uretouch.domain.generations.model.GenerationStatusType
import com.uretouch.feature.history.logic.history.api.state.GenerationUi.Status.Completed
import com.uretouch.feature.history.logic.history.api.state.GenerationUi.Status.Failed
import com.uretouch.feature.history.logic.history.api.state.GenerationUi.Status.InProgress

data class GenerationUi(
    val id: String,
    val url: String,
    val status: Status,
    val prompt: String,
    val generationUrls: List<String>,
) {
    val isClickable: Boolean = when (status) {
        Failed,
        InProgress,
        -> false

        Completed -> true
    }

    enum class Status {
        InProgress,
        Completed,
        Failed
    }
}

internal fun Generation.toUi(): GenerationUi {
    return GenerationUi(
        id = id,
        url = originalUrl,
        status = status.toUi(),
        generationUrls = generationUrls,
        prompt = prompt
    )
}

internal fun GenerationStatusType.toUi(): GenerationUi.Status {
    return when (this) {
        GenerationStatusType.InProgress -> InProgress
        GenerationStatusType.Completed -> Completed
        GenerationStatusType.Failed -> Failed
    }
}