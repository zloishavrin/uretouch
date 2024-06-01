package com.uretouch.data.auth.network

import com.uretouch.data.auth.network.model.ApiActiveStatus
import com.uretouch.data.auth.network.model.ApiLoginResponse

internal interface AuthApi {
    suspend fun login(email: String, password: String): ApiLoginResponse
    suspend fun registration(email: String, password: String): ApiLoginResponse
    suspend fun logout(refreshToken: String?)
    suspend fun checkActiveStatus(userId: String): ApiActiveStatus
}