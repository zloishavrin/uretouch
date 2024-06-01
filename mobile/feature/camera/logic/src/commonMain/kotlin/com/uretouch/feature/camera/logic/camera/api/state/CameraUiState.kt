package com.uretouch.feature.camera.logic.camera.api.state

import com.uretouch.feature.camera.logic.camera.internal.fsm.state.CameraState

class CameraUiState(

)

internal fun CameraState.toUiState(): CameraUiState {
    return when (this) {
        CameraState.Initial -> CameraUiState()
    }
}