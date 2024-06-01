package com.uretouch.feature.settings.logic.root.internal.di

import com.uretouch.feature.settings.logic.root.api.SettingsRootDependencies
import org.koin.core.module.Module
import org.koin.dsl.module

internal object SettingsRootModule {
    fun create(dependencies: SettingsRootDependencies): List<Module> {
        return listOf(
            module {
                factory { dependencies.authInteractor }
            }
        )
    }
}