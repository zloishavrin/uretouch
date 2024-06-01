package com.uretouch.feature.history.logic.history.internal

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.uretouch.common.core.decompose.CancelableCoroutineScope
import com.uretouch.common.core.decompose.cancelableCoroutineScope
import com.uretouch.common.core.decompose.defaultClosableScope
import com.uretouch.common.core.flow.AnyStateFlow
import com.uretouch.common.core.flow.wrapToAny
import com.uretouch.feature.history.logic.history.api.HistoryComponent
import com.uretouch.feature.history.logic.history.api.state.HistoryUiState
import com.uretouch.feature.history.logic.history.api.state.toUiState
import com.uretouch.feature.history.logic.history.internal.di.HistoryModule
import com.uretouch.feature.history.logic.history.internal.fsm.HistoryFeature
import com.uretouch.feature.history.logic.history.internal.fsm.state.HistoryState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.koin.core.parameter.parametersOf
import org.koin.core.scope.Scope

internal class DefaultHistoryComponent(
    componentContext: ComponentContext,
    rootScope: Scope,
) : HistoryComponent,
    ComponentContext by componentContext,
    CancelableCoroutineScope by componentContext.cancelableCoroutineScope() {

    private val scope by defaultClosableScope(rootScope = rootScope, modules = listOf(HistoryModule.module))

    private val initialState = HistoryState.initial()

    private val feature = instanceKeeper.getOrCreate {
        scope.get<HistoryFeature>(parameters = { parametersOf(initialState) })
    }

    override val state: AnyStateFlow<HistoryUiState> = feature.observeState()
        .map { it.toUiState() }
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.Eagerly,
            initialValue = initialState.toUiState()
        ).wrapToAny()
}