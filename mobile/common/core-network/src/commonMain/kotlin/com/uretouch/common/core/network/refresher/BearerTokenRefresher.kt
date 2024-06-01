package com.uretouch.common.core.network.refresher

import com.uretouch.common.core.eventDispatcher.AuthEventDispatcher
import com.uretouch.common.core.network.api.AuthRefresherApi
import com.uretouch.common.core.preference.AuthPreference
import io.ktor.client.plugins.auth.providers.BearerTokens
import kotlin.coroutines.cancellation.CancellationException

internal class BearerTokenRefresher(
    private val authRefresherApi: AuthRefresherApi,
    private val authPreference: AuthPreference,
    private val authEventDispatcher: AuthEventDispatcher,
) {
    fun getBearerTokens(): BearerTokens {
        return BearerTokens(
            accessToken = authPreference.getAccessToken(),
            refreshToken = authPreference.getRefreshToken()
        )
    }

    suspend fun refreshSessionOrLogout(): BearerTokens? {
        return try {
            val result = authRefresherApi.refresh(refreshToken = authPreference.getRefreshToken())
            val accessToken = result.accessToken
            val refreshToken = result.refreshToken
            authPreference.setAccessToken(accessToken)
            authPreference.setRefreshToken(refreshToken)

            BearerTokens(
                accessToken = accessToken,
                refreshToken = refreshToken
            )
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            authEventDispatcher.logout()
            null
        }
    }
}