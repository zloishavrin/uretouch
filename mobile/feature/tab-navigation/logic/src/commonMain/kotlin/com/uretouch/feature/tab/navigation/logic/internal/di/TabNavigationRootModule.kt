package com.uretouch.feature.tab.navigation.logic.internal.di

import com.uretouch.feature.tab.navigation.logic.api.TabNavigationRootDependencies
import org.koin.core.module.Module
import org.koin.dsl.module

internal object TabNavigationRootModule {
    fun create(dependencies: TabNavigationRootDependencies): List<Module> {
        return listOf(
            module {
                factory { dependencies.authInteractor }
                factory { dependencies.authEventDispatcher }
            }
        )
    }
}