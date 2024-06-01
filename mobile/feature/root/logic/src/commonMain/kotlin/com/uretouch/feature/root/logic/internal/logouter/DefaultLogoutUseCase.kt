package com.uretouch.feature.root.logic.internal.logouter

import com.uretouch.common.core.logouter.LogoutUseCase
import com.uretouch.common.core.network.extensions.HttpClientUtils.clearBearerTokens
import com.uretouch.common.core.preference.AuthPreference
import io.ktor.client.HttpClient

internal class DefaultLogoutUseCase(
    private val authPreference: AuthPreference,
    private val httpClient: HttpClient,
) : LogoutUseCase {
    override fun logout() {
        authPreference.clear()
        httpClient.clearBearerTokens()
    }
}