package com.uretouch.feature.camera.logic.photoPreview.internal.fsm.state

import ru.kontur.mobile.visualfsm.State

internal sealed class PhotoPreviewState : State {

    data class Initial(
        val photoPath: String,
        val prompt: String,
    ) : PhotoPreviewState()

    sealed class AsyncWorkerState : PhotoPreviewState() {
        data class UploadingPhoto(
            val photoPath: String,
            val prompt: String,
        ) : AsyncWorkerState()

        data class DeletingPhoto(
            val photoPath: String,
        ) : AsyncWorkerState()
    }

    sealed class NavigationState : PhotoPreviewState() {
        data class OnBack(
            val sourceState: AsyncWorkerState.DeletingPhoto,
        ) : NavigationState()
    }

    companion object {
        fun initial(photoPath: String): PhotoPreviewState {
            return Initial(photoPath = photoPath, prompt = "")
        }
    }
}