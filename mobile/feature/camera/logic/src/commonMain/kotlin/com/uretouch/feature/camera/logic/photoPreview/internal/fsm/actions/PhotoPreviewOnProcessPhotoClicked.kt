package com.uretouch.feature.camera.logic.photoPreview.internal.fsm.actions

import com.uretouch.feature.camera.logic.photoPreview.internal.fsm.state.PhotoPreviewState
import ru.kontur.mobile.visualfsm.Edge
import ru.kontur.mobile.visualfsm.Transition

internal class PhotoPreviewOnProcessPhotoClicked : BasePhotoPreviewAction() {

    @Edge("OnProcessPhotoClicked")
    inner class Blank : Transition<PhotoPreviewState.Initial, PhotoPreviewState.AsyncWorkerState.UploadingPhoto>() {
        override fun transform(state: PhotoPreviewState.Initial): PhotoPreviewState.AsyncWorkerState.UploadingPhoto {
            return PhotoPreviewState.AsyncWorkerState.UploadingPhoto(
                photoPath = state.photoPath,
                prompt = state.prompt,
                generationModes = state.generationModes,
                selectedMode = state.selectedMode
            )
        }
    }
}