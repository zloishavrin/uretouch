package com.uretouch.feature.history.logic.generationDetail.api

import com.uretouch.common.core.flow.AnyStateFlow
import com.uretouch.feature.history.logic.generationDetail.api.state.GenerationDetailUiState

interface GenerationDetailComponent {
    val state: AnyStateFlow<GenerationDetailUiState>

    fun onBackClick()
    fun onRefresh()
    fun onDownloadGenerationClick(url: String)
    fun onDownloadAllGenerationsClick()
}