package com.uretouch.feature.tab.navigation.logic.api

import com.uretouch.common.core.eventDispatcher.AuthEventDispatcher
import com.uretouch.common.core.util.SettingsOpener
import com.uretouch.domain.auth.interactor.AuthInteractor
import com.uretouch.domain.generations.interactor.GenerationsInteractor

class TabNavigationRootDependencies(
    val authInteractor: AuthInteractor,
    val authEventDispatcher: AuthEventDispatcher,
    val settingsOpener: SettingsOpener,
    val generationsRepository: GenerationsInteractor,
)