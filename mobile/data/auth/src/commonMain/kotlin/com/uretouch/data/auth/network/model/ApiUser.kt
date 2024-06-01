package com.uretouch.data.auth.network.model

import com.uretouch.domain.auth.model.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiUser(
    @SerialName("id") val id: String,
    @SerialName("email") val email: String,
    @SerialName("isActivated") val isActivated: Boolean,
) {
    fun toDomain(): User {
        return User(
            email = email,
            id = id,
            isActivated = isActivated
        )
    }
}