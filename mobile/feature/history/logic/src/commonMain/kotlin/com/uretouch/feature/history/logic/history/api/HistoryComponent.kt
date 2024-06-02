package com.uretouch.feature.history.logic.history.api

import com.uretouch.common.core.flow.AnyStateFlow
import com.uretouch.feature.history.logic.history.api.state.HistoryUiState

interface HistoryComponent {
    val state: AnyStateFlow<HistoryUiState>

    fun onRefresh()
}