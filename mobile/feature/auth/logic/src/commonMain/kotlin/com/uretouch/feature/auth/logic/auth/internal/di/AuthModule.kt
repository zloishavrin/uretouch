package com.uretouch.feature.auth.logic.auth.internal.di

import com.uretouch.feature.auth.logic.auth.internal.fsm.AuthAsyncWorker
import com.uretouch.feature.auth.logic.auth.internal.fsm.AuthFeature
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal object AuthModule {
    val module = module {
        factoryOf(::AuthAsyncWorker)
        factory { initialState -> AuthFeature(initialState = initialState.get(), asyncWorker = get()) }
    }
}