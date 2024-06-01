package com.uretouch.common.core.network.provider

import kotlinx.serialization.json.Json

internal class JsonProvider {
    fun get(): Json {
        return Json {
            isLenient = true
            ignoreUnknownKeys = true
            useAlternativeNames = false
            coerceInputValues = true
        }
    }
}