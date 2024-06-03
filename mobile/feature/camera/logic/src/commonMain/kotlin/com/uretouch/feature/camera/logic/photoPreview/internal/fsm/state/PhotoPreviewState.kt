package com.uretouch.feature.camera.logic.photoPreview.internal.fsm.state

import com.uretouch.domain.generations.model.GenerationMode
import ru.kontur.mobile.visualfsm.State

internal sealed class PhotoPreviewState : State {

    data class Initial(
        val photoPath: String,
        val prompt: String,
        val generationModes: List<GenerationMode>,
        val selectedMode: GenerationMode?,
    ) : PhotoPreviewState()

    sealed class AsyncWorkerState : PhotoPreviewState() {
        data class LoadingGenerationModes(
            val photoPath: String,
        ) : AsyncWorkerState()

        data class UploadingPhoto(
            val photoPath: String,
            val prompt: String,
            val selectedMode: GenerationMode?,
            val generationModes: List<GenerationMode>,
        ) : AsyncWorkerState()

        data class DeletingPhoto(
            val photoPath: String,
            val prompt: String,
            val selectedMode: GenerationMode?,
            val generationModes: List<GenerationMode>,
        ) : AsyncWorkerState()
    }

    sealed class NavigationState : PhotoPreviewState() {
        data class OnBack(
            val sourceState: AsyncWorkerState.DeletingPhoto,
        ) : NavigationState()
    }

    companion object {
        fun initial(photoPath: String): PhotoPreviewState {
            return AsyncWorkerState.LoadingGenerationModes(photoPath = photoPath)
        }
    }
}