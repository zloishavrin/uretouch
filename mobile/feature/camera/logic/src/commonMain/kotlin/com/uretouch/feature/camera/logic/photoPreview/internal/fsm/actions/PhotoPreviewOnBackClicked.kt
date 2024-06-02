package com.uretouch.feature.camera.logic.photoPreview.internal.fsm.actions

import com.uretouch.feature.camera.logic.photoPreview.internal.fsm.state.PhotoPreviewState
import ru.kontur.mobile.visualfsm.Edge
import ru.kontur.mobile.visualfsm.Transition

internal class PhotoPreviewOnBackClicked() : BasePhotoPreviewAction() {

    @Edge("OnBackClicked")
    inner class Blank : Transition<PhotoPreviewState.Initial, PhotoPreviewState.AsyncWorkerState.DeletingPhoto>() {
        override fun transform(state: PhotoPreviewState.Initial): PhotoPreviewState.AsyncWorkerState.DeletingPhoto {
            return PhotoPreviewState.AsyncWorkerState.DeletingPhoto(
                photoPath = state.photoPath
            )
        }
    }
}