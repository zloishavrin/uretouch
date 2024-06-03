package com.uretouch.feature.history.logic.generationDetail.internal.fsm.state

import com.uretouch.domain.generations.model.Generation
import ru.kontur.mobile.visualfsm.State

internal sealed class GenerationDetailState : State {

    sealed class AsyncWorkerState : GenerationDetailState() {
        data class Loading(
            val id: String,
        ) : AsyncWorkerState()

        data class DownloadGeneration(
            val sourceState: Loaded,
            val urls: List<String>,
        ) : AsyncWorkerState()
    }

    data class Loaded(
        val generation: Generation,
    ) : GenerationDetailState()

    data class Error(
        val id: String,
    ) : GenerationDetailState()


    companion object {
        fun initial(id: String): GenerationDetailState {
            return AsyncWorkerState.Loading(id = id)
        }
    }
}