package com.uretouch.feature.camera.logic.camera.internal.fsm.actions

import com.uretouch.feature.camera.logic.camera.internal.fsm.state.CameraState
import ru.kontur.mobile.visualfsm.Edge
import ru.kontur.mobile.visualfsm.Transition

internal class CameraOnPhotoClicked() : BaseCameraAction() {

    @Edge("OnPhotoClicked")
    inner class Blank : Transition<CameraState.Initial, CameraState.Initial>() {
        override fun transform(state: CameraState.Initial): CameraState.Initial {
            return CameraState.Initial
        }
    }
}