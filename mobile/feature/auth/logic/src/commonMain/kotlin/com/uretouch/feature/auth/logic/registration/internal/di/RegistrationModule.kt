package com.uretouch.feature.auth.logic.registration.internal.di

import com.uretouch.feature.auth.logic.registration.internal.fsm.RegistrationAsyncWorker
import com.uretouch.feature.auth.logic.registration.internal.fsm.RegistrationFeature
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal object RegistrationModule {
    val module = module {
        factoryOf(::RegistrationAsyncWorker)
        factory { initialState ->
            RegistrationFeature(
                initialState = initialState.get(),
                asyncWorker = get(),
            )
        }
    }
}