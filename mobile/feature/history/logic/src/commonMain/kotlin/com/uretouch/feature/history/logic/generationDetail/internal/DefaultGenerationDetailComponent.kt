package com.uretouch.feature.history.logic.generationDetail.internal

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.uretouch.common.core.decompose.CancelableCoroutineScope
import com.uretouch.common.core.decompose.cancelableCoroutineScope
import com.uretouch.common.core.decompose.defaultClosableScope
import com.uretouch.common.core.flow.AnyStateFlow
import com.uretouch.common.core.flow.wrapToAny
import com.uretouch.common.core.util.PlatformOpener
import com.uretouch.feature.history.logic.generationDetail.api.GenerationDetailComponent
import com.uretouch.feature.history.logic.generationDetail.api.state.GenerationDetailUiState
import com.uretouch.feature.history.logic.generationDetail.api.state.toUiState
import com.uretouch.feature.history.logic.generationDetail.internal.di.GenerationDetailModule
import com.uretouch.feature.history.logic.generationDetail.internal.fsm.GenerationDetailFeature
import com.uretouch.feature.history.logic.generationDetail.internal.fsm.actions.GenerationDetailOnDownloadAllGenerations
import com.uretouch.feature.history.logic.generationDetail.internal.fsm.actions.GenerationDetailOnDownloadGeneration
import com.uretouch.feature.history.logic.generationDetail.internal.fsm.actions.GenerationDetailOnRefreshed
import com.uretouch.feature.history.logic.generationDetail.internal.fsm.state.GenerationDetailState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.koin.core.parameter.parametersOf
import org.koin.core.scope.Scope

internal class DefaultGenerationDetailComponent(
    componentContext: ComponentContext,
    rootScope: Scope,
    generationId: String,
    private val navigateBack: () -> Unit,
) : GenerationDetailComponent,
    ComponentContext by componentContext,
    CancelableCoroutineScope by componentContext.cancelableCoroutineScope() {

    private val scope by defaultClosableScope(rootScope = rootScope, modules = listOf(GenerationDetailModule.module))

    private val initialState = GenerationDetailState.initial(id = generationId)

    private val feature = instanceKeeper.getOrCreate {
        scope.get<GenerationDetailFeature>(parameters = { parametersOf(initialState) })
    }

    override val state: AnyStateFlow<GenerationDetailUiState> = feature.observeState()
        .map { it.toUiState() }
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.Eagerly,
            initialValue = initialState.toUiState()
        ).wrapToAny()

    override fun onBackClick() {
        navigateBack()
    }

    override fun onRefresh() {
        feature.proceed(GenerationDetailOnRefreshed())
    }

    override fun onDownloadGenerationClick(url: String) {
        feature.proceed(GenerationDetailOnDownloadGeneration(url = url))
    }

    override fun onDownloadAllGenerationsClick() {
        feature.proceed(GenerationDetailOnDownloadAllGenerations())
    }

    override fun onShareClick(url: String) {
        scope.get<PlatformOpener>().shareLink(link = url)
    }
}