package com.uretouch.common.core.network.provider

import com.uretouch.common.core.network.httpconfig.commonHttpClientConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpResponseValidator
import kotlinx.serialization.json.Json

internal class AuthHttpClientProvider(
    private val engine: HttpClientEngine,
    private val json: Json,
) {
    fun get(): HttpClient {
        return HttpClient(engine) {
            commonHttpClientConfig(json)
            HttpResponseValidator {
                handleResponseExceptionWithRequest { exception, _ ->
                    val clientException = exception as? ClientRequestException ?: return@handleResponseExceptionWithRequest
                    val exceptionResponse = clientException.response
                    when (exceptionResponse.status) {
                        else -> throw clientException
                    }
                }
            }
        }
    }
}