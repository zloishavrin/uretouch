package com.uretouch.app.di

import com.uretouch.app.di.providers.EncryptedSharedPreferencesProvider
import com.uretouch.app.lifecycle.ContextHolder
import com.uretouch.app.util.AndroidImageUtil
import com.uretouch.app.util.AndroidPlatformOpener
import com.uretouch.common.core.settings.SettingsFactory
import com.uretouch.common.core.util.PlatformOpener
import com.uretouch.feature.root.logic.api.RootDependencies
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

object PlatformModule {
    val module = module {
        factoryOf(::EncryptedSharedPreferencesProvider)
        singleOf(EncryptedSharedPreferencesProvider::get)

        singleOf(::SettingsFactory)
        singleOf(::ContextHolder)
        singleOf(::AndroidPlatformOpener) bind PlatformOpener::class
        singleOf(::AndroidImageUtil) bind com.uretouch.domain.generations.util.ImageUtil::class

        factory {
            RootDependencies(
                settingsFactory = get(),
                platformOpener = get(),
                imageUtil = get()
            )
        }
    }
}