package com.uretouch.common.core.preference

interface AuthPreference {
    fun setAccessToken(token: String)
    fun setRefreshToken(token: String)
    fun getAccessToken(): String
    fun getRefreshToken(): String
    fun clear()
}