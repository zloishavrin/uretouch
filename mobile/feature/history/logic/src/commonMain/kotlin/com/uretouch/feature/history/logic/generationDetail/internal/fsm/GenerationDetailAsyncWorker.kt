package com.uretouch.feature.history.logic.generationDetail.internal.fsm

import com.uretouch.common.core.extensions.runCatchingCancellable
import com.uretouch.domain.generations.interactor.GenerationsInteractor
import com.uretouch.feature.history.logic.generationDetail.internal.fsm.actions.BaseGenerationDetailAction
import com.uretouch.feature.history.logic.generationDetail.internal.fsm.actions.GenerationDetailDownloadEnd
import com.uretouch.feature.history.logic.generationDetail.internal.fsm.actions.GenerationDetailHandleLoadedFail
import com.uretouch.feature.history.logic.generationDetail.internal.fsm.actions.GenerationDetailHandleLoadedSuccess
import com.uretouch.feature.history.logic.generationDetail.internal.fsm.state.GenerationDetailState
import kotlinx.coroutines.delay
import ru.kontur.mobile.visualfsm.AsyncWorker
import ru.kontur.mobile.visualfsm.AsyncWorkerTask

internal class GenerationDetailAsyncWorker(
    private val generationsInteractor: GenerationsInteractor,
) : AsyncWorker<GenerationDetailState, BaseGenerationDetailAction>() {
    override fun onNextState(state: GenerationDetailState): AsyncWorkerTask<GenerationDetailState> {
        return when (state) {
            is GenerationDetailState.AsyncWorkerState.Loading -> {
                AsyncWorkerTask.ExecuteIfNotExist(state) {
                    runCatchingCancellable {
                        generationsInteractor.getGeneration(id = state.id)
                    }.onSuccess {
                        proceed(GenerationDetailHandleLoadedSuccess(it))
                    }.onFailure {
                        proceed(GenerationDetailHandleLoadedFail())
                    }
                }
            }

            is GenerationDetailState.Error,
            is GenerationDetailState.Loaded,
            -> AsyncWorkerTask.Cancel()

            is GenerationDetailState.AsyncWorkerState.DownloadGeneration -> {
                AsyncWorkerTask.ExecuteIfNotExistWithSameClass(state) {
                    runCatchingCancellable {
                        state.urls.forEach { url ->
                            generationsInteractor.saveGenerationIntoDevice(generationUrl = url, prompt = state.sourceState.generation.prompt)
                            delay(1000)
                        }
                    }.onSuccess {
                        proceed(GenerationDetailDownloadEnd())
                    }.onFailure {
                        proceed(GenerationDetailDownloadEnd())
                    }

                }
            }
        }
    }
}