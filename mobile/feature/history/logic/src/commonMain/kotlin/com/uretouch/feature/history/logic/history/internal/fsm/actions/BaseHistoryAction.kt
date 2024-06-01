package com.uretouch.feature.history.logic.history.internal.fsm.actions

import com.uretouch.feature.history.logic.history.internal.fsm.state.HistoryState
import ru.kontur.mobile.visualfsm.Action

internal sealed class BaseHistoryAction : Action<HistoryState>()