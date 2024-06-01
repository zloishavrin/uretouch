package com.uretouch.feature.root.logic.internal.di

import com.uretouch.common.core.network.di.CoreNetworkModule
import com.uretouch.common.core.settings.SettingsFactory
import com.uretouch.data.onboarding.di.DataOnboardingModule
import com.uretouch.domain.onboarding.logic.di.DomainOnboardingModule
import com.uretouch.feature.root.logic.api.RootDependencies
import org.koin.core.module.Module
import org.koin.dsl.module

internal object RootModule {
    fun create(dependencies: RootDependencies): List<Module> {
        return listOf(
            module {
                factory { dependencies.settingsFactory }
                single { get<SettingsFactory>().createSettings() }
            },
        ) + listOf(
            DataOnboardingModule.module
        ) + listOf(
            DomainOnboardingModule.module
        ) + listOf(
            CoreNetworkModule.module,
        )
    }
}