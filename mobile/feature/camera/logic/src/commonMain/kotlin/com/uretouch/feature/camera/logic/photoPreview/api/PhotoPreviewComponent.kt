package com.uretouch.feature.camera.logic.photoPreview.api

import com.uretouch.common.core.flow.AnyFlow
import com.uretouch.common.core.flow.AnyStateFlow
import com.uretouch.feature.camera.logic.photoPreview.api.state.PhotoPreviewUiState

interface PhotoPreviewComponent {
    val state: AnyStateFlow<PhotoPreviewUiState>
    val showMessageFlow: AnyFlow<Pair<String, Boolean>>

    fun onBackClick()
    fun onProcessPhotoClick()
    fun onCancelClick()
    fun onPromptChange(text: String)
    fun onGenerationModeClick(modeId: String)
}