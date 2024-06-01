package com.uretouch.data.auth

import com.uretouch.common.core.preference.AuthPreference
import com.uretouch.data.auth.network.AuthApi
import com.uretouch.data.auth.network.model.ApiLoginResponse
import com.uretouch.domain.auth.AuthRepository
import com.uretouch.domain.auth.model.User

internal class DefaultAuthRepository(
    private val authApi: AuthApi,
    private val authPreference: AuthPreference,
) : AuthRepository {
    override suspend fun login(email: String, password: String): User {
        val response = authApi.login(email = email, password = password)
        saveTokens(response)
        return response.user.toDomain()
    }

    override suspend fun registration(email: String, password: String): User {
        val response = authApi.registration(email = email, password = password)
        saveTokens(response)
        return response.user.toDomain()
    }

    private fun saveTokens(response: ApiLoginResponse) {
        authPreference.setAccessToken(response.accessToken)
        authPreference.setRefreshToken(response.refreshToken)
    }

    override suspend fun logout() {
        authApi.logout(refreshToken = authPreference.getRefreshToken())
    }

    override suspend fun checkActiveStatus(userId: String): Boolean {
        return authApi.checkActiveStatus(userId = userId).status
    }

    override fun isAuthorized(): Boolean {
        return authPreference.isAuthorized()
    }

    override fun setIsAuthorized(isAuthorized: Boolean) {
        return authPreference.setIsAuthorized(isAuthorized = isAuthorized)
    }

}