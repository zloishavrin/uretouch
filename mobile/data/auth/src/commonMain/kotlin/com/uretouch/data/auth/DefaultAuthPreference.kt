package com.uretouch.data.auth

import com.russhwolf.settings.Settings
import com.russhwolf.settings.set
import com.uretouch.common.core.preference.AuthPreference

internal class DefaultAuthPreference(
    private val settings: Settings,
) : AuthPreference {
    override fun setAccessToken(token: String) {
        settings[AccessTokenKey] = token
    }

    override fun setRefreshToken(token: String) {
        settings[RefreshTokenKey] = token
    }

    override fun getAccessToken(): String {
        return settings.getString(AccessTokenKey, "")
    }

    override fun getRefreshToken(): String {
        return settings.getString(RefreshTokenKey, "")
    }

    override fun clear() {
        settings[AccessTokenKey] = null
        settings[RefreshTokenKey] = null
    }
}

private const val AccessTokenKey = "AccessTokenKey_v1"
private const val RefreshTokenKey = "RefreshTokenKey_v1"