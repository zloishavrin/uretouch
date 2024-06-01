package com.uretouch.feature.auth.logic.root.internal.di

import com.uretouch.feature.auth.logic.root.api.AuthRootDependencies
import org.koin.core.module.Module
import org.koin.dsl.module

internal object AuthRootModule {
    fun create(dependencies: AuthRootDependencies): List<Module> {
        return listOf(
            module {
                factory { dependencies.authInteractor }
            }
        )
    }
}