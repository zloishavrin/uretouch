package com.uretouch.common.core.network.provider

import com.uretouch.common.core.exceptions.ServerException
import com.uretouch.common.core.network.api.model.ApiError
import com.uretouch.common.core.network.httpconfig.commonHttpClientConfig
import com.uretouch.common.core.network.refresher.BearerTokenRefresher
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.bearer
import kotlinx.serialization.json.Json

internal class HttpClientProvider(
    private val engine: HttpClientEngine,
    private val json: Json,
    private val bearerTokenRefresher: BearerTokenRefresher,
) {
    fun get(): HttpClient {
        return HttpClient(engine) {
            commonHttpClientConfig(json)
            install(Auth) {
                bearer {
                    loadTokens {
                        bearerTokenRefresher.getBearerTokens()
                    }
                    refreshTokens {
                        bearerTokenRefresher.refreshSessionOrLogout()
                    }
                }
            }

            HttpResponseValidator {
                handleResponseExceptionWithRequest { exception, request ->
                    val clientException = exception as? ClientRequestException ?: return@handleResponseExceptionWithRequest
                    val apiException = try {
                        clientException.response.body<ApiError>()
                    } catch (e: Exception) {
                        null
                    }
                    if (apiException != null) {
                        throw ServerException.RequestError(message = apiException.message)
                    } else {
                        throw ServerException.UnknownError(message = "Непредвиденная ошибка")
                    }

                }
            }
        }
    }
}