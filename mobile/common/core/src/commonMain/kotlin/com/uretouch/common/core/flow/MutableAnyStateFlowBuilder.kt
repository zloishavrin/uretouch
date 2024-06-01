package com.uretouch.common.core.flow

import kotlinx.coroutines.flow.MutableStateFlow

fun <T : Any> MutableAnyStateFlow(initialValue: T): AnyStateFlow<T> {
    return MutableStateFlow(initialValue).wrapToAny()
}