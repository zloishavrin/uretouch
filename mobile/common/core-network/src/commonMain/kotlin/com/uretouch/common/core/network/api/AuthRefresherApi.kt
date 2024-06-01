package com.uretouch.common.core.network.api

import com.uretouch.common.core.network.api.model.ApiRefreshTokenResponse

internal interface AuthRefresherApi {
    suspend fun refresh(refreshToken: String): ApiRefreshTokenResponse
}