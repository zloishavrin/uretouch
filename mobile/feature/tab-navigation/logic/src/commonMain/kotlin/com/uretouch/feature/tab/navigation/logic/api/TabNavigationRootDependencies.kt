package com.uretouch.feature.tab.navigation.logic.api

import com.uretouch.common.core.eventDispatcher.AuthEventDispatcher
import com.uretouch.domain.auth.interactor.AuthInteractor

class TabNavigationRootDependencies(
    val authInteractor: AuthInteractor,
    val authEventDispatcher: AuthEventDispatcher,
)