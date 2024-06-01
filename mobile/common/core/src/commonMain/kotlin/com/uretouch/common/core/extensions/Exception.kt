package com.uretouch.common.core.extensions

import io.github.aakira.napier.Napier
import kotlin.coroutines.cancellation.CancellationException

inline fun <R> runCatchingCancellable(block: () -> R): Result<R> {
    return try {
        Result.success(block())
    } catch (e: CancellationException) {
        throw e
    } catch (e: Throwable) {
        Napier.e(message = e.message.orEmpty(), throwable = e)
        Result.failure(e)
    }
}