package com.uretouch.feature.history.logic.history.api.state

import com.uretouch.feature.history.logic.history.internal.fsm.state.HistoryState

data class HistoryUiState(
    val isLoading: Boolean,
)

internal fun HistoryState.toUiState(): HistoryUiState {
    return when (this) {
        HistoryState.AsyncWorkerState.Loading -> {
            HistoryUiState(isLoading = true)
        }
    }
}