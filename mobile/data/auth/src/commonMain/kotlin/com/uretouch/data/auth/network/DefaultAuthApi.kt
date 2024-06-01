package com.uretouch.data.auth.network

import com.uretouch.data.auth.network.model.ApiActiveStatus
import com.uretouch.data.auth.network.model.ApiLoginRequest
import com.uretouch.data.auth.network.model.ApiLoginResponse
import com.uretouch.data.auth.network.model.ApiLogoutRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.path

internal class DefaultAuthApi(
    private val httpClient: HttpClient,
) : AuthApi {
    override suspend fun login(email: String, password: String): ApiLoginResponse {
        return httpClient.post {
            url {
                path("/api/auth/login")
            }
            setBody(ApiLoginRequest(email = email, password = password))
        }.body()
    }

    override suspend fun registration(email: String, password: String): ApiLoginResponse {
        return httpClient.post {
            url {
                path("/api/auth/registration")
            }
            setBody(ApiLoginRequest(email = email, password = password))
        }.body()
    }

    override suspend fun logout(refreshToken: String?) {
        httpClient.post {
            url {
                path("/api/auth/logout")
            }
            setBody(ApiLogoutRequest(refreshToken = refreshToken))
        }
    }

    override suspend fun checkActiveStatus(userId: String): ApiActiveStatus {
        return httpClient.get {
            url {
                path("/api/auth/isActivated/${userId}")
            }
        }.body()
    }
}