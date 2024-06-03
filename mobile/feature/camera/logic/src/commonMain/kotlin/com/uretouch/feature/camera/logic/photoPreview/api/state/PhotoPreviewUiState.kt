package com.uretouch.feature.camera.logic.photoPreview.api.state

import com.uretouch.domain.generations.model.GenerationMode
import com.uretouch.feature.camera.logic.photoPreview.internal.fsm.state.PhotoPreviewState

data class GenerationModeUi(
    val id: String,
    val name: String,
    val description: String,
)

internal fun GenerationMode.toUi(): GenerationModeUi {
    return GenerationModeUi(
        id = id, name = name, description = description
    )
}

data class PhotoPreviewUiState(
    val photoPath: String,
    val isUploadingPhoto: Boolean,
    val prompt: String,
    val generationModes: List<GenerationModeUi>,
    val selectedMode: GenerationModeUi?,
) {
    val isGenerationModeVisible: Boolean = generationModes.isNotEmpty()
    val isCustomGenerationEnabled: Boolean = selectedMode == null
    val isSendEnabled: Boolean = selectedMode != null || prompt.isNotBlank()
}

internal fun PhotoPreviewState.toUiState(): PhotoPreviewUiState {
    return when (this) {
        is PhotoPreviewState.AsyncWorkerState.UploadingPhoto -> {
            PhotoPreviewUiState(
                photoPath = photoPath,
                isUploadingPhoto = true,
                prompt = selectedMode?.description ?: prompt,
                generationModes = generationModes.map { it.toUi() },
                selectedMode = selectedMode?.toUi()
            )
        }

        is PhotoPreviewState.Initial -> PhotoPreviewUiState(
            photoPath = photoPath,
            isUploadingPhoto = false,
            prompt = selectedMode?.description ?: prompt,
            generationModes = generationModes.map { it.toUi() },
            selectedMode = selectedMode?.toUi()
        )

        is PhotoPreviewState.NavigationState.OnBack -> sourceState.toUiState()
        is PhotoPreviewState.AsyncWorkerState.DeletingPhoto -> {
            PhotoPreviewUiState(
                photoPath = photoPath,
                isUploadingPhoto = true,
                prompt = "",
                generationModes = generationModes.map { it.toUi() },
                selectedMode = selectedMode?.toUi()
            )
        }

        is PhotoPreviewState.AsyncWorkerState.LoadingGenerationModes -> {
            PhotoPreviewUiState(
                photoPath = photoPath,
                isUploadingPhoto = false,
                prompt = "",
                generationModes = listOf(),
                selectedMode = null
            )
        }
    }
}