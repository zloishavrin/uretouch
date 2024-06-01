package com.uretouch.common.core.network.extensions

import io.ktor.client.HttpClient
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerAuthProvider
import io.ktor.client.plugins.plugin

object HttpClientUtils {
    fun HttpClient.clearBearerTokens() {
        this.plugin(Auth).providers.filterIsInstance<BearerAuthProvider>().firstOrNull()?.clearToken()
    }
}
