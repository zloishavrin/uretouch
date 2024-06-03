package com.uretouch.feature.camera.logic.photoPreview.internal.fsm.actions

import com.uretouch.domain.generations.model.GenerationMode
import com.uretouch.feature.camera.logic.photoPreview.internal.fsm.state.PhotoPreviewState
import ru.kontur.mobile.visualfsm.Transition

internal class PhotoPreviewHandleLoadMode(
    private val modes: List<GenerationMode>,
) : BasePhotoPreviewAction() {
    inner class Blank : Transition<PhotoPreviewState.AsyncWorkerState.LoadingGenerationModes, PhotoPreviewState.Initial>() {
        override fun transform(state: PhotoPreviewState.AsyncWorkerState.LoadingGenerationModes): PhotoPreviewState.Initial {
            return PhotoPreviewState.Initial(
                photoPath = state.photoPath,
                prompt = "",
                generationModes = modes,
                selectedMode = null
            )
        }
    }
}