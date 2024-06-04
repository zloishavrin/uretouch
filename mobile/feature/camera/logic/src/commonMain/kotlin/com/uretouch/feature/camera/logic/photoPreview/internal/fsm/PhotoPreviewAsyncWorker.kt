package com.uretouch.feature.camera.logic.photoPreview.internal.fsm

import com.uretouch.common.core.extensions.runCatchingCancellable
import com.uretouch.domain.generations.interactor.GenerationsInteractor
import com.uretouch.domain.generations.util.ImageUtil
import com.uretouch.feature.camera.logic.photoPreview.internal.fsm.actions.BasePhotoPreviewAction
import com.uretouch.feature.camera.logic.photoPreview.internal.fsm.actions.PhotoPreviewHandleLoadMode
import com.uretouch.feature.camera.logic.photoPreview.internal.fsm.actions.PhotoPreviewHandlePhotoDeleted
import com.uretouch.feature.camera.logic.photoPreview.internal.fsm.actions.PhotoPreviewHandlePhotoUploadSuccess
import com.uretouch.feature.camera.logic.photoPreview.internal.fsm.state.PhotoPreviewState
import ru.kontur.mobile.visualfsm.AsyncWorker
import ru.kontur.mobile.visualfsm.AsyncWorkerTask

internal class PhotoPreviewAsyncWorker(
    private val generationsInteractor: GenerationsInteractor,
    private val imageUtil: ImageUtil,
) : AsyncWorker<PhotoPreviewState, BasePhotoPreviewAction>() {
    override fun onNextState(state: PhotoPreviewState): AsyncWorkerTask<PhotoPreviewState> {
        return when (state) {
            is PhotoPreviewState.AsyncWorkerState.UploadingPhoto -> {
                AsyncWorkerTask.ExecuteIfNotExistWithSameClass(state) {
                    runCatchingCancellable {
                        println("1234 ${state.selectedMode?.id}")
                        generationsInteractor.createGeneration(path = state.photoPath, prompt = state.prompt, modeId = state.selectedMode?.id)
                    }.onSuccess {
                        proceed(PhotoPreviewHandlePhotoUploadSuccess())
                    }.onFailure {
                        proceed(PhotoPreviewHandlePhotoUploadSuccess())
                    }
                }
            }

            is PhotoPreviewState.AsyncWorkerState.DeletingPhoto -> {
                AsyncWorkerTask.ExecuteIfNotExistWithSameClass(state) {
                    runCatchingCancellable {
                        imageUtil.deleteImage(path = state.photoPath)
                    }.onSuccess {
                        proceed(PhotoPreviewHandlePhotoDeleted())
                    }.onFailure {
                        proceed(PhotoPreviewHandlePhotoDeleted())
                    }
                }
            }

            is PhotoPreviewState.AsyncWorkerState.LoadingGenerationModes -> {
                AsyncWorkerTask.ExecuteIfNotExist(state) {
                    runCatchingCancellable {
                        generationsInteractor.getGenerationModes()
                    }.onSuccess {
                        proceed(PhotoPreviewHandleLoadMode(it))
                    }.onFailure {
                        proceed(PhotoPreviewHandleLoadMode(listOf()))
                    }
                }
            }

            is PhotoPreviewState.Initial,
            is PhotoPreviewState.NavigationState,
            -> AsyncWorkerTask.Cancel()
        }
    }
}