package com.uretouch.feature.camera.logic.photoPreview.internal.fsm

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.uretouch.feature.camera.logic.photoPreview.internal.fsm.actions.BasePhotoPreviewAction
import com.uretouch.feature.camera.logic.photoPreview.internal.fsm.state.PhotoPreviewState
import ru.kontur.mobile.visualfsm.Feature
import ru.kontur.mobile.visualfsm.GenerateTransitionsFactory

@GenerateTransitionsFactory
internal class PhotoPreviewFeature(
    initialState: PhotoPreviewState,
    private val asyncWorker: PhotoPreviewAsyncWorker,
) : InstanceKeeper.Instance, Feature<PhotoPreviewState, BasePhotoPreviewAction>(
    initialState = initialState,
    asyncWorker = asyncWorker,
    transitionsFactory = GeneratedPhotoPreviewFeatureTransitionsFactory(),
) {
    override fun onDestroy() {
        asyncWorker.unbind()
    }
}