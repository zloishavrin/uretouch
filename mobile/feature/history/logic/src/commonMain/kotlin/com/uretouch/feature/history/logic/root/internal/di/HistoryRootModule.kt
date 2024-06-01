package com.uretouch.feature.history.logic.root.internal.di

import com.uretouch.feature.history.logic.root.api.HistoryRootDependencies
import org.koin.core.module.Module
import org.koin.dsl.module

internal object HistoryRootModule {
    fun create(dependencies: HistoryRootDependencies): List<Module> {
        return listOf(
            module {

            }
        )
    }
}