package com.uretouch.feature.camera.logic.camera.api.state

import com.uretouch.feature.camera.logic.camera.internal.fsm.state.CameraState

data class CameraUiState(
    val isEnabledPhoto: Boolean,
    val isSaving: Boolean,
)

internal fun CameraState.toUiState(): CameraUiState {
    return when (this) {
        CameraState.Initial -> {
            CameraUiState(isEnabledPhoto = true, isSaving = false)
        }

        is CameraState.AsyncWorkerState.SavingPhoto -> {
            CameraUiState(isEnabledPhoto = false, isSaving = true)
        }

        is CameraState.NavigationState.ToPhotoPreview -> sourceState.toUiState()
    }
}