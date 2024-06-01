package com.uretouch.feature.auth.logic.registration.api

import com.uretouch.common.core.flow.AnyStateFlow
import com.uretouch.feature.auth.logic.registration.api.state.RegistrationUiState

interface RegistrationComponent {
    val state: AnyStateFlow<RegistrationUiState>

    fun passwordChange(password: String)
    fun repeatPasswordChange(password: String)
    fun emailChange(email: String)

    fun onRegistryClick()
    fun onBackClick()
}