package com.uretouch.domain.auth.interactor

import com.uretouch.domain.auth.model.User

interface AuthInteractor {
    suspend fun login(email: String, password: String): User
    suspend fun logout()
    suspend fun registration(email: String, password: String): User
    suspend fun checkActiveStatus(userId: String): Boolean
    fun isAuthorized(): Boolean
}