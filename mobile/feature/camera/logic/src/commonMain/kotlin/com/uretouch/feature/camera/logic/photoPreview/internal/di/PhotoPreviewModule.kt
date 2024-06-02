package com.uretouch.feature.camera.logic.photoPreview.internal.di

import com.uretouch.feature.camera.logic.photoPreview.internal.fsm.PhotoPreviewAsyncWorker
import com.uretouch.feature.camera.logic.photoPreview.internal.fsm.PhotoPreviewFeature
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal object PhotoPreviewModule {
    val module = module {
        factoryOf(::PhotoPreviewAsyncWorker)
        factory { initialState ->
            PhotoPreviewFeature(
                initialState = initialState.get(),
                asyncWorker = get(),
            )
        }
    }
}