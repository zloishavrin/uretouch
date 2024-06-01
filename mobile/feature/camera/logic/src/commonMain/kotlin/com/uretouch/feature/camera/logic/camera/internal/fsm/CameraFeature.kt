package com.uretouch.feature.camera.logic.camera.internal.fsm

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.uretouch.feature.camera.logic.camera.internal.fsm.actions.BaseCameraAction
import com.uretouch.feature.camera.logic.camera.internal.fsm.state.CameraState
import ru.kontur.mobile.visualfsm.Feature
import ru.kontur.mobile.visualfsm.GenerateTransitionsFactory

@GenerateTransitionsFactory
internal class CameraFeature(
    initialState: CameraState,
    private val asyncWorker: CameraAsyncWorker,
) : InstanceKeeper.Instance, Feature<CameraState, BaseCameraAction>(
    initialState = initialState,
    asyncWorker = asyncWorker,
    transitionsFactory = GeneratedCameraFeatureTransitionsFactory(),
) {
    override fun onDestroy() {
        asyncWorker.unbind()
    }
}