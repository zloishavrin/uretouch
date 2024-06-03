package com.uretouch.feature.history.logic.generationDetail.internal.di

import com.uretouch.feature.history.logic.generationDetail.internal.fsm.GenerationDetailAsyncWorker
import com.uretouch.feature.history.logic.generationDetail.internal.fsm.GenerationDetailFeature
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal object GenerationDetailModule {
    val module = module {
        factoryOf(::GenerationDetailAsyncWorker)
        factory { initialState ->
            GenerationDetailFeature(
                initialState = initialState.get(),
                asyncWorker = get(),
            )
        }
    }
}