package com.uretouch.common.core.network.provider

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin

internal actual class HttpClientEngineProvider {
    actual fun get(): HttpClientEngine {
        return Darwin.create {}
    }
}