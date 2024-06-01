package com.uretouch.feature.camera.logic.camera.internal.fsm.actions

import com.uretouch.feature.camera.logic.camera.internal.fsm.state.CameraState
import ru.kontur.mobile.visualfsm.Action

internal sealed class BaseCameraAction : Action<CameraState>()