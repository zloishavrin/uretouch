package com.uretouch.feature.camera.logic.photoPreview.internal.fsm

import com.uretouch.common.core.extensions.runCatchingCancellable
import com.uretouch.domain.generations.interactor.GenerationsInteractor
import com.uretouch.domain.generations.util.ImageUtil
import com.uretouch.feature.camera.logic.photoPreview.internal.dispatcher.PhotoPreviewEvent
import com.uretouch.feature.camera.logic.photoPreview.internal.dispatcher.PhotoPreviewEventDispatcher
import com.uretouch.feature.camera.logic.photoPreview.internal.fsm.actions.BasePhotoPreviewAction
import com.uretouch.feature.camera.logic.photoPreview.internal.fsm.actions.PhotoPreviewHandleLoadMode
import com.uretouch.feature.camera.logic.photoPreview.internal.fsm.actions.PhotoPreviewHandlePhotoDeleted
import com.uretouch.feature.camera.logic.photoPreview.internal.fsm.actions.PhotoPreviewHandlePhotoUploadFail
import com.uretouch.feature.camera.logic.photoPreview.internal.fsm.actions.PhotoPreviewHandlePhotoUploadSuccess
import com.uretouch.feature.camera.logic.photoPreview.internal.fsm.state.PhotoPreviewState
import ru.kontur.mobile.visualfsm.AsyncWorker
import ru.kontur.mobile.visualfsm.AsyncWorkerTask

internal class PhotoPreviewAsyncWorker(
    private val generationsInteractor: GenerationsInteractor,
    private val imageUtil: ImageUtil,
    private val dispatcher: PhotoPreviewEventDispatcher,
) : AsyncWorker<PhotoPreviewState, BasePhotoPreviewAction>() {
    override fun onNextState(state: PhotoPreviewState): AsyncWorkerTask<PhotoPreviewState> {
        return when (state) {
            is PhotoPreviewState.AsyncWorkerState.UploadingPhoto -> {
                AsyncWorkerTask.ExecuteIfNotExistWithSameClass(state) {
                    runCatchingCancellable {
                        generationsInteractor.createGeneration(path = state.photoPath, prompt = state.prompt, modeId = state.selectedMode?.id)
                    }.onSuccess {
                        dispatcher.dispatchEvent(
                            PhotoPreviewEvent.ShowMessage(
                                text = "Фото успешно загружено, результаты обработки будут в истории",
                                isError = false
                            )
                        )
                        proceed(PhotoPreviewHandlePhotoUploadSuccess())
                    }.onFailure {
                        dispatcher.dispatchEvent(PhotoPreviewEvent.ShowMessage(text = "При загрузке фото произошла ошибка", isError = true))
                        proceed(PhotoPreviewHandlePhotoUploadFail())
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