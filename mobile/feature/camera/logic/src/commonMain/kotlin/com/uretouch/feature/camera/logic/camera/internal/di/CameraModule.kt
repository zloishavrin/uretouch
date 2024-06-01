package com.uretouch.feature.camera.logic.camera.internal.di

import com.uretouch.feature.camera.logic.camera.internal.fsm.CameraAsyncWorker
import com.uretouch.feature.camera.logic.camera.internal.fsm.CameraFeature
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal object CameraModule {
    val module = module {
        factoryOf(::CameraAsyncWorker)
        factory { initialState ->
            CameraFeature(
                initialState = initialState.get(),
                asyncWorker = get(),
            )
        }
    }
}