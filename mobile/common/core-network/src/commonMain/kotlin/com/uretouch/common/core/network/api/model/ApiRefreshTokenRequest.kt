package com.uretouch.common.core.network.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ApiRefreshTokenRequest(
    @SerialName("refreshToken") val refreshToken: String,
)