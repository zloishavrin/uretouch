package com.uretouch.feature.camera.logic.camera.api

import com.uretouch.common.core.flow.AnyStateFlow
import com.uretouch.feature.camera.logic.camera.api.state.CameraUiState

interface CameraComponent {
    val state: AnyStateFlow<CameraUiState>

    fun onPhotoCapture(image: ByteArray?)
    fun onOpenSettingsClick()
}