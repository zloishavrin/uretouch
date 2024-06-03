package com.uretouch.feature.camera.logic.photoPreview.internal.fsm.actions

import com.uretouch.feature.camera.logic.photoPreview.internal.fsm.state.PhotoPreviewState
import ru.kontur.mobile.visualfsm.Edge
import ru.kontur.mobile.visualfsm.SelfTransition

internal class PhotoPreviewOnGenerationModeClicked(
    private val modeId: String,
) : BasePhotoPreviewAction() {

    @Edge("OnGenerationModeClicked")
    inner class Blank : SelfTransition<PhotoPreviewState.Initial>() {
        override fun transform(state: PhotoPreviewState.Initial): PhotoPreviewState.Initial {
            val selectedMode = if (modeId == state.selectedMode?.id) {
                null
            } else {
                state.generationModes.find { it.id == modeId }
            }
            return state.copy(selectedMode = selectedMode)
        }
    }
}