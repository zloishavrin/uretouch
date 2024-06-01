package com.uretouch.common.core.eventDispatcher

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

abstract class BaseEventDispatcher<Event : Any> {
    private val sharedFlow = MutableSharedFlow<Event>()

    suspend fun dispatchEvent(event: Event) {
        sharedFlow.emit(event)
    }

    fun observeEvents(): Flow<Event> {
        return sharedFlow.asSharedFlow()
    }
}