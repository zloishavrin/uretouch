package com.uretouch.feature.camera.logic.photoPreview.internal.fsm.actions

import com.uretouch.feature.camera.logic.photoPreview.internal.fsm.state.PhotoPreviewState
import ru.kontur.mobile.visualfsm.Transition

internal class PhotoPreviewHandlePhotoUploadSuccess : BasePhotoPreviewAction() {
    inner class Blank : Transition<PhotoPreviewState.AsyncWorkerState.UploadingPhoto, PhotoPreviewState.Initial>() {
        override fun transform(state: PhotoPreviewState.AsyncWorkerState.UploadingPhoto): PhotoPreviewState.Initial {
            return PhotoPreviewState.Initial(
                photoPath = state.photoPath,
                prompt = if (state.selectedMode != null) state.prompt else "",
                generationModes = state.generationModes,
                selectedMode = state.selectedMode
            )
        }
    }
}