package com.uretouch.feature.settings.logic.settings.internal.fsm.actions

import com.uretouch.feature.settings.logic.settings.internal.fsm.state.SettingsState
import ru.kontur.mobile.visualfsm.Action

internal sealed class BaseSettingsAction : Action<SettingsState>()