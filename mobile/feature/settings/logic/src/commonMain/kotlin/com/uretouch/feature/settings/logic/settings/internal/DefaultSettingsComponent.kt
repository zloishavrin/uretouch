package com.uretouch.feature.settings.logic.settings.internal

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.uretouch.common.core.decompose.CancelableCoroutineScope
import com.uretouch.common.core.decompose.cancelableCoroutineScope
import com.uretouch.common.core.decompose.defaultClosableScope
import com.uretouch.common.core.flow.AnyStateFlow
import com.uretouch.common.core.flow.wrapToAny
import com.uretouch.feature.settings.logic.settings.api.SettingsComponent
import com.uretouch.feature.settings.logic.settings.api.state.SettingsUiState
import com.uretouch.feature.settings.logic.settings.api.state.toUiState
import com.uretouch.feature.settings.logic.settings.internal.di.SettingsModule
import com.uretouch.feature.settings.logic.settings.internal.fsm.SettingsFeature
import com.uretouch.feature.settings.logic.settings.internal.fsm.actions.SettingsOnLogoutClicked
import com.uretouch.feature.settings.logic.settings.internal.fsm.state.SettingsState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.koin.core.parameter.parametersOf
import org.koin.core.scope.Scope

internal class DefaultSettingsComponent(
    componentContext: ComponentContext,
    rootScope: Scope,
) : SettingsComponent,
    ComponentContext by componentContext,
    CancelableCoroutineScope by componentContext.cancelableCoroutineScope() {

    private val scope by defaultClosableScope(rootScope = rootScope, modules = listOf(SettingsModule.module))

    private val initialState = SettingsState.initial()

    private val feature = instanceKeeper.getOrCreate {
        scope.get<SettingsFeature>(parameters = { parametersOf(initialState) })
    }

    override val state: AnyStateFlow<SettingsUiState> = feature.observeState()
        .map { it.toUiState() }
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.Eagerly,
            initialValue = initialState.toUiState()
        ).wrapToAny()

    override fun onLogoutClick() {
        feature.proceed(SettingsOnLogoutClicked())
    }
}