package com.uretouch.feature.camera.logic.camera.internal.fsm.actions

import com.uretouch.feature.camera.logic.camera.internal.fsm.state.CameraState
import ru.kontur.mobile.visualfsm.Edge
import ru.kontur.mobile.visualfsm.Transition

internal class CameraHandlePhotoSaved(
    private val path: String,
) : BaseCameraAction() {

    @Edge("PhotoSaved")
    inner class Blank : Transition<CameraState.AsyncWorkerState.SavingPhoto, CameraState.NavigationState.ToPhotoPreview>() {

        override fun transform(state: CameraState.AsyncWorkerState.SavingPhoto): CameraState.NavigationState.ToPhotoPreview {
            return CameraState.NavigationState.ToPhotoPreview(
                sourceState = state,
                path = path
            )
        }
    }
}