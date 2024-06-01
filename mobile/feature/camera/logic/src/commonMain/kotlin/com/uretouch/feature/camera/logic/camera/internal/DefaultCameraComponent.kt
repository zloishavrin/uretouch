package com.uretouch.feature.camera.logic.camera.internal

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.uretouch.common.core.decompose.CancelableCoroutineScope
import com.uretouch.common.core.decompose.cancelableCoroutineScope
import com.uretouch.common.core.decompose.defaultClosableScope
import com.uretouch.common.core.flow.AnyStateFlow
import com.uretouch.common.core.flow.wrapToAny
import com.uretouch.feature.camera.logic.camera.api.CameraComponent
import com.uretouch.feature.camera.logic.camera.api.state.CameraUiState
import com.uretouch.feature.camera.logic.camera.api.state.toUiState
import com.uretouch.feature.camera.logic.camera.internal.di.CameraModule
import com.uretouch.feature.camera.logic.camera.internal.fsm.CameraFeature
import com.uretouch.feature.camera.logic.camera.internal.fsm.state.CameraState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.koin.core.parameter.parametersOf
import org.koin.core.scope.Scope

internal class DefaultCameraComponent(
    componentContext: ComponentContext,
    rootScope: Scope,
) : CameraComponent,
    ComponentContext by componentContext,
    CancelableCoroutineScope by componentContext.cancelableCoroutineScope() {

    private val scope by defaultClosableScope(rootScope = rootScope, modules = listOf(CameraModule.module))

    private val initialState = CameraState.initial()

    private val feature = instanceKeeper.getOrCreate {
        scope.get<CameraFeature>(parameters = { parametersOf(initialState) })
    }

    override val state: AnyStateFlow<CameraUiState> = feature.observeState()
        .map { it.toUiState() }
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.Eagerly,
            initialValue = initialState.toUiState()
        ).wrapToAny()

    override fun onPhotoClick() {

    }
}