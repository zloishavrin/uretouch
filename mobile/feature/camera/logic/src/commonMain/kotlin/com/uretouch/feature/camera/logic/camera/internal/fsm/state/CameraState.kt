package com.uretouch.feature.camera.logic.camera.internal.fsm.state

import ru.kontur.mobile.visualfsm.State

internal sealed class CameraState : State {

    data object Initial : CameraState()

    sealed class AsyncWorkerState : CameraState() {

    }

    companion object {
        fun initial(): CameraState {
            return Initial
        }
    }
}