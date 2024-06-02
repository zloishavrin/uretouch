package com.uretouch.feature.camera.logic.photoPreview.internal.fsm.actions

import com.uretouch.feature.camera.logic.photoPreview.internal.fsm.state.PhotoPreviewState
import ru.kontur.mobile.visualfsm.Transition

internal class PhotoPreviewHandlePhotoDeleted() : BasePhotoPreviewAction() {
    inner class Blank : Transition<PhotoPreviewState.AsyncWorkerState.DeletingPhoto, PhotoPreviewState.NavigationState.OnBack>() {
        override fun transform(state: PhotoPreviewState.AsyncWorkerState.DeletingPhoto): PhotoPreviewState.NavigationState.OnBack {
            return PhotoPreviewState.NavigationState.OnBack(
                sourceState = state
            )
        }
    }
}