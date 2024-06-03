package com.uretouch.feature.history.logic.generationDetail.api.state

import com.uretouch.feature.history.logic.generationDetail.internal.fsm.state.GenerationDetailState
import com.uretouch.feature.history.logic.history.api.state.GenerationUi
import com.uretouch.feature.history.logic.history.api.state.toUi

sealed class GenerationDetailUiState {
    data object Loading : GenerationDetailUiState()
    data object Error : GenerationDetailUiState()
    data class Loaded(
        val generationUi: GenerationUi,
    ) : GenerationDetailUiState()
}

internal fun GenerationDetailState.toUiState(): GenerationDetailUiState {
    return when (this) {
        is GenerationDetailState.AsyncWorkerState.Loading -> GenerationDetailUiState.Loading
        is GenerationDetailState.Error -> GenerationDetailUiState.Error
        is GenerationDetailState.Loaded -> GenerationDetailUiState.Loaded(
            generationUi = generation.toUi()
        )

        is GenerationDetailState.AsyncWorkerState.DownloadGeneration -> sourceState.toUiState()
    }
}