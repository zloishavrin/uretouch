package com.uretouch.feature.camera.logic.photoPreview.internal

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.backhandler.BackCallback
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.uretouch.common.core.decompose.CancelableCoroutineScope
import com.uretouch.common.core.decompose.cancelableCoroutineScope
import com.uretouch.common.core.decompose.defaultClosableScope
import com.uretouch.common.core.flow.AnyStateFlow
import com.uretouch.common.core.flow.wrapToAny
import com.uretouch.feature.camera.logic.photoPreview.api.PhotoPreviewComponent
import com.uretouch.feature.camera.logic.photoPreview.api.state.PhotoPreviewUiState
import com.uretouch.feature.camera.logic.photoPreview.api.state.toUiState
import com.uretouch.feature.camera.logic.photoPreview.internal.di.PhotoPreviewModule
import com.uretouch.feature.camera.logic.photoPreview.internal.fsm.PhotoPreviewFeature
import com.uretouch.feature.camera.logic.photoPreview.internal.fsm.actions.PhotoPreviewOnBackClicked
import com.uretouch.feature.camera.logic.photoPreview.internal.fsm.actions.PhotoPreviewOnGenerationModeClicked
import com.uretouch.feature.camera.logic.photoPreview.internal.fsm.actions.PhotoPreviewOnProcessPhotoClicked
import com.uretouch.feature.camera.logic.photoPreview.internal.fsm.actions.PhotoPreviewOnPromptChanged
import com.uretouch.feature.camera.logic.photoPreview.internal.fsm.state.PhotoPreviewState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import org.koin.core.parameter.parametersOf
import org.koin.core.scope.Scope

internal class DefaultPhotoPreviewComponent(
    componentContext: ComponentContext,
    rootScope: Scope,
    photoPath: String,
    private val navigateBack: () -> Unit,
) : PhotoPreviewComponent,
    ComponentContext by componentContext,
    CancelableCoroutineScope by componentContext.cancelableCoroutineScope() {

    private val scope by defaultClosableScope(rootScope = rootScope, modules = listOf(PhotoPreviewModule.module))

    private val initialState = PhotoPreviewState.initial(photoPath = photoPath)

    private val feature = instanceKeeper.getOrCreate {
        scope.get<PhotoPreviewFeature>(parameters = { parametersOf(initialState) })
    }

    override val state: AnyStateFlow<PhotoPreviewUiState> = feature.observeState()
        .map { it.toUiState() }
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.Eagerly,
            initialValue = initialState.toUiState()
        ).wrapToAny()

    init {
        backHandler.register(BackCallback { onBackClick() })
        feature.observeState()
            .filterIsInstance<PhotoPreviewState.NavigationState>()
            .onEach { state ->
                when (state) {
                    is PhotoPreviewState.NavigationState.OnBack -> {
                        navigateBack()
                    }
                }
            }.launchIn(coroutineScope)
    }

    override fun onBackClick() {
        feature.proceed(PhotoPreviewOnBackClicked())
    }

    override fun onProcessPhotoClick() {
        feature.proceed(PhotoPreviewOnProcessPhotoClicked())
    }

    override fun onCancelClick() {
        feature.proceed(PhotoPreviewOnBackClicked())
    }

    override fun onPromptChange(text: String) {
        feature.proceed(PhotoPreviewOnPromptChanged(prompt = text))
    }

    override fun onGenerationModeClick(modeId: String) {
        feature.proceed(PhotoPreviewOnGenerationModeClicked(modeId = modeId))
    }
}