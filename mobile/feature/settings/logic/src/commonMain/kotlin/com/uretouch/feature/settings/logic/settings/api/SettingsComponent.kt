package com.uretouch.feature.settings.logic.settings.api

import com.uretouch.common.core.flow.AnyStateFlow
import com.uretouch.feature.settings.logic.settings.api.state.SettingsUiState

interface SettingsComponent {
    val state: AnyStateFlow<SettingsUiState>

    fun onLogoutClick()
}