package com.uretouch.feature.auth.logic.root.api

import com.uretouch.domain.auth.interactor.AuthInteractor

class AuthRootDependencies(
    val authInteractor: AuthInteractor,
)