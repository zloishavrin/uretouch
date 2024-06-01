package com.uretouch.feature.auth.logic.auth.internal.fsm.actions

import com.uretouch.feature.auth.logic.auth.internal.fsm.state.AuthState
import ru.kontur.mobile.visualfsm.Action

internal sealed class BaseAuthAction : Action<AuthState>()