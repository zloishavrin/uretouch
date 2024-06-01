package com.uretouch.common.core.extensions

import com.arkivanov.essenty.lifecycle.Lifecycle
import com.arkivanov.essenty.lifecycle.LifecycleOwner
import com.arkivanov.essenty.lifecycle.doOnDestroy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlin.coroutines.CoroutineContext

private val mainContext: CoroutineContext
    get() = SupervisorJob() + Dispatchers.Main.immediate

fun mainCoroutineScope(): CoroutineScope {
    return CoroutineScope(mainContext)
}

fun LifecycleOwner.lifecycleScope(context: CoroutineContext): CoroutineScope {
    return CoroutineScope(context, lifecycle)
}

fun LifecycleOwner.mainLifecycleScope(): CoroutineScope {
    return lifecycleScope(mainContext)
}

private fun CoroutineScope(context: CoroutineContext, lifecycle: Lifecycle): CoroutineScope {
    val scope = CoroutineScope(context)
    lifecycle.doOnDestroy(scope::cancel)
    return scope
}