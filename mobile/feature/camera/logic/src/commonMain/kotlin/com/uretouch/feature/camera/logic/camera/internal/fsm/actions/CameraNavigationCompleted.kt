package com.uretouch.feature.camera.logic.camera.internal.fsm.actions

import com.uretouch.feature.camera.logic.camera.internal.fsm.state.CameraState
import ru.kontur.mobile.visualfsm.Edge
import ru.kontur.mobile.visualfsm.Transition

internal class CameraNavigationCompleted() : BaseCameraAction() {

    @Edge("NavigationCompleted")
    inner class Blank : Transition<CameraState.NavigationState.ToPhotoPreview, CameraState.Initial>() {
        override fun transform(state: CameraState.NavigationState.ToPhotoPreview): CameraState.Initial {
            return CameraState.Initial
        }
    }
}