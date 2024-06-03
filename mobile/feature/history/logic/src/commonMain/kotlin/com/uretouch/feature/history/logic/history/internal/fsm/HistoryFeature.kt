package com.uretouch.feature.history.logic.history.internal.fsm

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.uretouch.common.core.extensions.mainCoroutineScope
import com.uretouch.domain.generations.interactor.GenerationsInteractor
import com.uretouch.feature.history.logic.history.internal.fsm.actions.BaseHistoryAction
import com.uretouch.feature.history.logic.history.internal.fsm.actions.HistoryHandleLoadingSuccess
import com.uretouch.feature.history.logic.history.internal.fsm.state.HistoryState
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.kontur.mobile.visualfsm.Feature
import ru.kontur.mobile.visualfsm.GenerateTransitionsFactory

@GenerateTransitionsFactory
internal class HistoryFeature(
    initialState: HistoryState,
    private val asyncWorker: HistoryAsyncWorker,
    private val generationsInteractor: GenerationsInteractor,
) : InstanceKeeper.Instance, Feature<HistoryState, BaseHistoryAction>(
    initialState = initialState,
    asyncWorker = asyncWorker,
    transitionsFactory = GeneratedHistoryFeatureTransitionsFactory(),
) {
    private val coroutineScope = mainCoroutineScope()
    private var updaterJob: Job? = null

    private val pooling = flow {
        while (true) {
            val generations = runCatching { generationsInteractor.getGenerationsHistory() }.getOrNull()
            if (generations != null) {
                emit(generations)
            }
            delay(5000L)
        }
    }

    fun startUpdater() {
        updaterJob = pooling.onEach { generations ->
            proceed(HistoryHandleLoadingSuccess(generations))
        }.launchIn(coroutineScope)
    }

    fun stopUpdater() {
        updaterJob?.cancel()
        updaterJob = null
    }

    override fun onDestroy() {
        coroutineScope.cancel()
        asyncWorker.unbind()
    }
}