package com.uretouch.domain.auth.interactor

import com.uretouch.domain.auth.AuthRepository
import com.uretouch.domain.auth.model.User

internal class DefaultAuthInteractor(
    private val authRepository: AuthRepository,
) : AuthInteractor {
    override suspend fun login(email: String, password: String): User {
        return authRepository.login(email = email, password = password).also {
            authRepository.setIsAuthorized(isAuthorized = it.isActivated)
        }
    }

    override suspend fun logout() {
        authRepository.logout()
    }

    override suspend fun registration(email: String, password: String): User {
        return authRepository.registration(email = email, password = password).also {
            authRepository.setIsAuthorized(isAuthorized = it.isActivated)
        }
    }

    override suspend fun checkActiveStatus(userId: String): Boolean {
        return authRepository.checkActiveStatus(userId = userId).also { isActivated ->
            authRepository.setIsAuthorized(isAuthorized = isActivated)
        }
    }

    override fun isAuthorized(): Boolean {
        return authRepository.isAuthorized()
    }
}