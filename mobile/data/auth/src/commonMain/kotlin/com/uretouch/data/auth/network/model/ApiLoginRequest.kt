package com.uretouch.data.auth.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ApiLoginRequest(
    @SerialName("email") val email: String,
    @SerialName("password") val password: String,
)