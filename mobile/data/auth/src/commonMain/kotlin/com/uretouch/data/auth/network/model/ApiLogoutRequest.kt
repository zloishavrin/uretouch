package com.uretouch.data.auth.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ApiLogoutRequest(
    @SerialName("refreshToken") val refreshToken: String?,
)