package com.uretouch.domain.auth.interactor

import com.uretouch.domain.auth.AuthRepository
import com.uretouch.domain.auth.model.User

internal class DefaultAuthInteractor(
    private val authRepository: AuthRepository,
) : AuthInteractor {
    override suspend fun login(email: String, password: String): User {
        return authRepository.login(email = email, password = password)
    }

    override suspend fun logout() {
        authRepository.logout()
    }

    override suspend fun registration(email: String, password: String): User {
        return authRepository.registration(email = email, password = password)
    }

    override suspend fun checkActiveStatus(userId: String): Boolean {
        return authRepository.checkActiveStatus(userId = userId)
    }
}