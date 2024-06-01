package com.uretouch.feature.auth.logic.auth.api

import com.uretouch.common.core.flow.AnyStateFlow
import com.uretouch.feature.auth.logic.auth.api.state.AuthUiState

interface AuthComponent {
    val state: AnyStateFlow<AuthUiState>

    fun emailChange(text: String)
    fun passwordChange(text: String)
    fun onLoginClick()
    fun onRegistrationClick()
}