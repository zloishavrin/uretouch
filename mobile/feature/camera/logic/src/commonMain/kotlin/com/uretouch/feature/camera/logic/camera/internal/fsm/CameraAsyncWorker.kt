package com.uretouch.feature.camera.logic.camera.internal.fsm

import com.uretouch.common.core.extensions.runCatchingCancellable
import com.uretouch.domain.generations.util.ImageUtil
import com.uretouch.feature.camera.logic.camera.internal.fsm.actions.BaseCameraAction
import com.uretouch.feature.camera.logic.camera.internal.fsm.actions.CameraHandlePhotoSaved
import com.uretouch.feature.camera.logic.camera.internal.fsm.state.CameraState
import ru.kontur.mobile.visualfsm.AsyncWorker
import ru.kontur.mobile.visualfsm.AsyncWorkerTask

internal class CameraAsyncWorker(
    private val imageUtil: ImageUtil,
) : AsyncWorker<CameraState, BaseCameraAction>() {
    override fun onNextState(state: CameraState): AsyncWorkerTask<CameraState> {
        return when (state) {
            is CameraState.AsyncWorkerState.SavingPhoto -> {
                AsyncWorkerTask.ExecuteIfNotExistWithSameClass(state) {
                    runCatchingCancellable {
                        imageUtil.saveImage(image = state.image)
                    }.onSuccess {
                        proceed(CameraHandlePhotoSaved(it))
                    }
                }
            }

            is CameraState.NavigationState,
            CameraState.Initial,
            -> AsyncWorkerTask.Cancel()
        }
    }
}