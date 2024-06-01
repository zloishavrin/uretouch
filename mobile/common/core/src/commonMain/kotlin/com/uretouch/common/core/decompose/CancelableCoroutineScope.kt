package com.uretouch.common.core.decompose

import com.arkivanov.essenty.lifecycle.LifecycleOwner
import com.arkivanov.essenty.lifecycle.doOnDestroy
import com.uretouch.common.core.extensions.mainCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel

interface CancelableCoroutineScope {
    val coroutineScope: CoroutineScope

    fun cancelCoroutineScope()
}

fun LifecycleOwner.cancelableCoroutineScope(): CancelableCoroutineScope {
    val scope = DefaultCancelableCoroutineScope()
    lifecycle.doOnDestroy(scope::cancelCoroutineScope)
    return scope
}

private class DefaultCancelableCoroutineScope : CancelableCoroutineScope {
    override val coroutineScope: CoroutineScope = mainCoroutineScope()

    override fun cancelCoroutineScope() {
        coroutineScope.cancel()
    }
}
