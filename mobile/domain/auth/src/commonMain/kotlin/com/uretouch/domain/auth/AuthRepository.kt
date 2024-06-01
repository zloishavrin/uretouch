package com.uretouch.domain.auth

import com.uretouch.domain.auth.model.User

interface AuthRepository {
    suspend fun login(email: String, password: String): User
    suspend fun registration(email: String, password: String): User
    suspend fun logout()
    suspend fun checkActiveStatus(userId: String): Boolean
    fun isAuthorized(): Boolean
    fun setIsAuthorized(isAuthorized: Boolean)
}