package com.uretouch.data.auth.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiActiveStatus(
    @SerialName("status") val status: Boolean,
)