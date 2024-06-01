package com.uretouch.feature.settings.logic.settings.internal.di

import com.uretouch.feature.settings.logic.settings.internal.fsm.SettingsAsyncWorker
import com.uretouch.feature.settings.logic.settings.internal.fsm.SettingsFeature
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal object SettingsModule {
    val module = module {
        factoryOf(::SettingsAsyncWorker)
        factory { initialState ->
            SettingsFeature(
                initialState = initialState.get(),
                asyncWorker = get(),
            )
        }
    }
}