package com.uretouch.common.core.network.api

import com.uretouch.common.core.network.api.model.ApiRefreshTokenRequest
import com.uretouch.common.core.network.api.model.ApiRefreshTokenResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.path

internal class DefaultAuthRefresherApi(
    private val httpClient: HttpClient,
) : AuthRefresherApi {
    override suspend fun refresh(refreshToken: String): ApiRefreshTokenResponse {
        return httpClient.post {
            url {
                path("/auth/refresh")
            }
            setBody(ApiRefreshTokenRequest(refreshToken = refreshToken))
        }.body()
    }
}