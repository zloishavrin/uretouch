package com.uretouch.feature.camera.logic.photoPreview.api

import com.uretouch.common.core.flow.AnyStateFlow
import com.uretouch.feature.camera.logic.photoPreview.api.state.PhotoPreviewUiState

interface PhotoPreviewComponent {
    val state: AnyStateFlow<PhotoPreviewUiState>

    fun onBackClick()
    fun onProcessPhotoClick()
    fun onCancelClick()
    fun onPromptChange(text: String)
}