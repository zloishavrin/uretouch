package com.uretouch.feature.history.logic.history.api.state

import com.uretouch.feature.history.logic.history.internal.fsm.state.HistoryState

sealed class HistoryUiState {
    data object Loading : HistoryUiState()

    data class Loaded(
        val generations: List<GenerationUi>,
        val isRefresh: Boolean,
    ) : HistoryUiState()

    data object Error : HistoryUiState()
}

internal fun HistoryState.toUiState(): HistoryUiState {
    return when (this) {
        is HistoryState.AsyncWorkerState.Loading -> {
            when (sourceState) {
                null,
                is HistoryState.AsyncWorkerState.Loading,
                HistoryState.Error,
                -> HistoryUiState.Loading

                is HistoryState.Loaded -> {
                    HistoryUiState.Loaded(
                        generations = sourceState.generations.map { it.toUi() },
                        isRefresh = true
                    )
                }
            }
        }

        HistoryState.Error -> HistoryUiState.Error
        is HistoryState.Loaded -> {
            HistoryUiState.Loaded(
                generations = generations.map { it.toUi() },
                isRefresh = false
            )
        }
    }
}