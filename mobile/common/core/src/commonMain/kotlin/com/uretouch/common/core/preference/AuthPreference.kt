package com.uretouch.common.core.preference

interface AuthPreference {
    fun setAccessToken(token: String)
    fun setRefreshToken(token: String)
    fun getAccessToken(): String
    fun getRefreshToken(): String
    fun isAuthorized(): Boolean
    fun setIsAuthorized(isAuthorized: Boolean)
    fun clear()
}