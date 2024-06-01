package com.uretouch.feature.auth.logic.checking.api.state

data class CheckingUiState(
    val email: String,
) {
    companion object {
        fun initial(email: String): CheckingUiState {
            return CheckingUiState(email = email)
        }
    }
}