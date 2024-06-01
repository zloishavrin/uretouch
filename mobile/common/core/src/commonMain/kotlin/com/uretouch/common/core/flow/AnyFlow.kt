package com.uretouch.common.core.flow

import com.uretouch.common.core.extensions.mainCoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class AnyFlow<T : Any>(
    private val source: Flow<T>,
) : Flow<T> by source {
    fun collect(
        onEach: (T) -> Unit,
    ): Cancellable {
        val scope = mainCoroutineScope()

        this.source.onEach {
            onEach(it)
        }.launchIn(scope)

        return object : Cancellable {
            override fun cancel() {
                scope.cancel()
            }
        }
    }
}

fun <T : Any> Flow<T>.wrapToAny(): AnyFlow<T> = AnyFlow(this)