package com.uretouch.common.core.network.provider

import io.ktor.client.engine.HttpClientEngine

internal expect class HttpClientEngineProvider() {
    fun get(): HttpClientEngine
}