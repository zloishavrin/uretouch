package com.uretouch.feature.root.logic.internal.di

import com.uretouch.common.core.database.di.CoreDatabaseModule
import com.uretouch.common.core.eventDispatcher.AuthEventDispatcher
import com.uretouch.common.core.logouter.LogoutUseCase
import com.uretouch.common.core.network.di.CoreNetworkModule
import com.uretouch.common.core.settings.SettingsFactory
import com.uretouch.data.auth.di.DataAuthModule
import com.uretouch.data.generations.di.DataGenerationsModule
import com.uretouch.data.onboarding.di.DataOnboardingModule
import com.uretouch.domain.auth.di.DomainAuthModule
import com.uretouch.domain.generations.di.DomainGenerationsModule
import com.uretouch.domain.onboarding.logic.di.DomainOnboardingModule
import com.uretouch.feature.root.logic.api.RootDependencies
import com.uretouch.feature.root.logic.internal.logouter.DefaultLogoutUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal object RootModule {
    fun create(dependencies: RootDependencies): List<Module> {
        return listOf(
            module {
                factory { dependencies.settingsFactory }
                factory { dependencies.settingsOpener }
                factory { dependencies.imageUtil }
                single { Dispatchers.IO }
                single { get<SettingsFactory>().createSettings() }
                singleOf(::AuthEventDispatcher)
                singleOf(::DefaultLogoutUseCase) bind LogoutUseCase::class
            },
        ) + listOf(
            DataOnboardingModule.module,
            DataAuthModule.module,
            DataGenerationsModule.module,
        ) + listOf(
            DomainOnboardingModule.module,
            DomainAuthModule.module,
            DomainGenerationsModule.module,
        ) + listOf(
            CoreDatabaseModule.module,
            CoreNetworkModule.module,
        )
    }
}