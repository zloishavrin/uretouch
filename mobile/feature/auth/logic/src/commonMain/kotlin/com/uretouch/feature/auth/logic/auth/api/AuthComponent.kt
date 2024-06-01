package com.uretouch.feature.auth.logic.auth.api

import com.uretouch.feature.auth.logic.auth.api.state.AuthUiState
import kotlinx.coroutines.flow.StateFlow

interface AuthComponent {
    val state: StateFlow<AuthUiState>

    fun loginChange(text: String)
    fun passwordChange(text: String)
    fun onLoginClick()
}