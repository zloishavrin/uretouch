package com.uretouch.common.core.flow

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

fun <T : Any> EmptyAnyFlow(): AnyFlow<T> {
    return Channel<T>().receiveAsFlow().wrapToAny()
}