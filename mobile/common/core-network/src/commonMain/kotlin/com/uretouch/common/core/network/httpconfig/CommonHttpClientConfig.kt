package com.uretouch.common.core.network.httpconfig

import com.uretouch.common.core.environment.Environment
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

fun HttpClientConfig<*>.commonHttpClientConfig(json: Json) {
    expectSuccess = true
    install(ContentNegotiation) {
        json(json)
    }
    if (Environment.isRelease.not()) {
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Napier.d(message)
                }
            }
            level = LogLevel.ALL
        }
    }
    install(HttpTimeout) {
        val timeout = 30000L
        connectTimeoutMillis = timeout
        requestTimeoutMillis = timeout
        socketTimeoutMillis = timeout
    }
    install(DefaultRequest) {
        header(HttpHeaders.ContentType, ContentType.Application.Json)
    }
}