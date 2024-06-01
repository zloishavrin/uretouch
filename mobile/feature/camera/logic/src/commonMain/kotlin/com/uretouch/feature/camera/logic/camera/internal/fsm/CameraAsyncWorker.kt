package com.uretouch.feature.camera.logic.camera.internal.fsm

import com.uretouch.feature.camera.logic.camera.internal.fsm.actions.BaseCameraAction
import com.uretouch.feature.camera.logic.camera.internal.fsm.state.CameraState
import ru.kontur.mobile.visualfsm.AsyncWorker
import ru.kontur.mobile.visualfsm.AsyncWorkerTask

internal class CameraAsyncWorker() : AsyncWorker<CameraState, BaseCameraAction>() {
    override fun onNextState(state: CameraState): AsyncWorkerTask<CameraState> {
        return when (state) {
            else -> AsyncWorkerTask.Cancel()
        }
    }
}