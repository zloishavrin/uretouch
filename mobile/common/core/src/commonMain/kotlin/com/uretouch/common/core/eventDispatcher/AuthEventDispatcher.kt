package com.uretouch.common.core.eventDispatcher

class AuthEventDispatcher : BaseEventDispatcher<AuthEvent>() {
    suspend fun logout() {
        dispatchEvent(AuthEvent.Logout)
    }
}

sealed class AuthEvent : DispatchEvent {
    data object Authorize : AuthEvent()
    data object Logout : AuthEvent()
}