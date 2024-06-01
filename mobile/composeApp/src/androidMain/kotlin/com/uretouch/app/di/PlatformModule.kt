package com.uretouch.app.di

import com.uretouch.app.di.providers.EncryptedSharedPreferencesProvider
import com.uretouch.common.core.settings.SettingsFactory
import com.uretouch.feature.root.logic.api.RootDependencies
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object PlatformModule {
    val module = module {
        factoryOf(::EncryptedSharedPreferencesProvider)
        singleOf(EncryptedSharedPreferencesProvider::get)

        singleOf(::SettingsFactory)

        factory {
            RootDependencies(
                settingsFactory = get()
            )
        }
    }
}