package com.uretouch.feature.history.logic.history.internal.di

import com.uretouch.feature.history.logic.history.internal.fsm.HistoryAsyncWorker
import com.uretouch.feature.history.logic.history.internal.fsm.HistoryFeature
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal object HistoryModule {
    val module = module {
        factoryOf(::HistoryAsyncWorker)
        factory { initialState ->
            HistoryFeature(
                initialState = initialState.get(),
                asyncWorker = get(),
                generationsInteractor = get()
            )
        }
    }
}