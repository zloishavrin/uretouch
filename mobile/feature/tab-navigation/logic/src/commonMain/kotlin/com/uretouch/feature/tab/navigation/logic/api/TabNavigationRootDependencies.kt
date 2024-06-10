package com.uretouch.feature.tab.navigation.logic.api

import com.uretouch.common.core.eventDispatcher.AuthEventDispatcher
import com.uretouch.common.core.util.PlatformOpener
import com.uretouch.domain.auth.interactor.AuthInteractor
import com.uretouch.domain.generations.interactor.GenerationsInteractor

class TabNavigationRootDependencies(
    val authInteractor: AuthInteractor,
    val authEventDispatcher: AuthEventDispatcher,
    val platformOpener: PlatformOpener,
    val generationsRepository: GenerationsInteractor,
)