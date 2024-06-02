package com.uretouch.feature.camera.logic.photoPreview.api.state

import com.uretouch.feature.camera.logic.photoPreview.internal.fsm.state.PhotoPreviewState

data class PhotoPreviewUiState(
    val photoPath: String,
    val isLoading: Boolean,
    val prompt: String,
) {
    val isCustomPromptVisible: Boolean = true
}

internal fun PhotoPreviewState.toUiState(): PhotoPreviewUiState {
    return when (this) {
        is PhotoPreviewState.AsyncWorkerState.UploadingPhoto -> {
            PhotoPreviewUiState(photoPath = photoPath, isLoading = true, prompt = prompt)
        }

        is PhotoPreviewState.Initial -> PhotoPreviewUiState(photoPath = photoPath, isLoading = false, prompt = prompt)

        is PhotoPreviewState.NavigationState.OnBack -> sourceState.toUiState()
        is PhotoPreviewState.AsyncWorkerState.DeletingPhoto -> {
            PhotoPreviewUiState(photoPath = photoPath, isLoading = true, prompt = "")
        }
    }
}