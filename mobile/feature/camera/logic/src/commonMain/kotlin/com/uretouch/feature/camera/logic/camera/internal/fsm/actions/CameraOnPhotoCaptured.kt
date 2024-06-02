package com.uretouch.feature.camera.logic.camera.internal.fsm.actions

import com.uretouch.feature.camera.logic.camera.internal.fsm.state.CameraState
import ru.kontur.mobile.visualfsm.Edge
import ru.kontur.mobile.visualfsm.Transition

internal class CameraOnPhotoCaptured(
    private val image: ByteArray?,
) : BaseCameraAction() {

    @Edge("OnPhotoCaptured")
    inner class Blank : Transition<CameraState.Initial, CameraState.AsyncWorkerState.SavingPhoto>() {
        override fun predicate(state: CameraState.Initial): Boolean {
            return image != null
        }

        override fun transform(state: CameraState.Initial): CameraState.AsyncWorkerState.SavingPhoto {
            return CameraState.AsyncWorkerState.SavingPhoto(
                image = requireNotNull(image)
            )
        }
    }
}