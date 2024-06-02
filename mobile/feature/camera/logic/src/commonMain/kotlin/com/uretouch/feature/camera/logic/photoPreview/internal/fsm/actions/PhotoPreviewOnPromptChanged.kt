package com.uretouch.feature.camera.logic.photoPreview.internal.fsm.actions

import com.uretouch.feature.camera.logic.photoPreview.internal.fsm.state.PhotoPreviewState
import ru.kontur.mobile.visualfsm.Edge
import ru.kontur.mobile.visualfsm.SelfTransition

internal class PhotoPreviewOnPromptChanged(
    private val prompt: String,
) : BasePhotoPreviewAction() {

    @Edge("OnPromptChanged")
    inner class Blank : SelfTransition<PhotoPreviewState.Initial>() {
        override fun predicate(state: PhotoPreviewState.Initial): Boolean {
            return prompt.length <= 200
        }

        override fun transform(state: PhotoPreviewState.Initial): PhotoPreviewState.Initial {
            return state.copy(
                prompt = prompt
            )
        }
    }
}