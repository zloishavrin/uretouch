package com.uretouch.feature.root.logic.internal.di

import com.uretouch.common.core.logouter.LogoutUseCase
import com.uretouch.common.core.network.di.CoreNetworkModule
import com.uretouch.common.core.settings.SettingsFactory
import com.uretouch.data.auth.di.DataAuthModule
import com.uretouch.data.onboarding.di.DataOnboardingModule
import com.uretouch.domain.auth.di.DomainAuthModule
import com.uretouch.domain.onboarding.logic.di.DomainOnboardingModule
import com.uretouch.feature.root.logic.api.RootDependencies
import com.uretouch.feature.root.logic.internal.logouter.DefaultLogoutUseCase
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.kontur.logistics.common.core.eventDispatcher.AuthEventDispatcher

internal object RootModule {
    fun create(dependencies: RootDependencies): List<Module> {
        return listOf(
            module {
                factory { dependencies.settingsFactory }
                single { get<SettingsFactory>().createSettings() }

                singleOf(::AuthEventDispatcher)
                singleOf(::DefaultLogoutUseCase) bind LogoutUseCase::class
            },
        ) + listOf(
            DataOnboardingModule.module,
            DataAuthModule.module,
        ) + listOf(
            DomainOnboardingModule.module,
            DomainAuthModule.module
        ) + listOf(
            CoreNetworkModule.module,
        )
    }
}